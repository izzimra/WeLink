package com.example.a207944_izzi_izwan_lab02

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.File

@Composable
fun ScanScreen(
    navController: NavController,
    viewModel: AppViewModel
) {
    val context = LocalContext.current

    // The recognized text (editable so the user can fix OCR mistakes before saving).
    var recognizedText by remember { mutableStateOf("") }
    var courseCode by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("Point the camera at a page of notes.") }

    // Holds the file location where the camera will write the captured photo.
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // Launcher #2: opens the camera app, writes full-res photo to imageUri, returns success.
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        val uri = imageUri
        if (success && uri != null) {
            status = "Reading text..."
            runTextRecognition(
                context = context,
                uri = uri,
                onResult = { text ->
                    recognizedText = text
                    status = if (text.isBlank()) "No text found. Try again." else "Text extracted!"
                },
                onError = { status = "Error: $it" }
            )
        }
    }

    // Launcher #1: asks the user for CAMERA permission at runtime (required since Android 6).
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            val uri = createImageUri(context)
            imageUri = uri
            cameraLauncher.launch(uri)
        } else {
            status = "Camera permission denied."
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Scan Material", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface)
            Text("Camera + on-device OCR (ML Kit)", fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant)

            Text(status, fontSize = 13.sp, color = MaterialTheme.colorScheme.primary)

            // Capture button: check permission first, then launch the camera.
            Button(
                onClick = {
                    val alreadyGranted = ContextCompat.checkSelfPermission(
                        context, Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED

                    if (alreadyGranted) {
                        val uri = createImageUri(context)
                        imageUri = uri
                        cameraLauncher.launch(uri)
                    } else {
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) { Text("Open Camera & Scan", fontWeight = FontWeight.Bold) }

            // Editable recognized text.
            OutlinedTextField(
                value = recognizedText,
                onValueChange = { recognizedText = it },
                label = { Text("Extracted text") },
                modifier = Modifier.fillMaxWidth().height(160.dp),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = courseCode,
                onValueChange = { courseCode = it },
                label = { Text("Course Code (optional)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            // Save the scanned text into Room via the existing ViewModel API.
            Button(
                onClick = {
                    if (recognizedText.isNotBlank()) {
                        viewModel.addPost(
                            MaterialPost(
                                title = recognizedText.take(80), // first 80 chars as the title
                                courseCode = courseCode.ifBlank { "Scanned" },
                                materialType = "Scanned"
                            )
                        )
                        navController.navigate("profile")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) { Text("Save to My Posts", fontWeight = FontWeight.Bold) }

            OutlinedButton(
                onClick = { navController.navigate("home") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) { Text("Back to Home") }
        }
    }
}

// Creates a temp file in the app's cache and returns a shareable content:// URI for it.
private fun createImageUri(context: Context): Uri {
    val file = File.createTempFile("scan_", ".jpg", context.cacheDir)
    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider", // must match the authority in the manifest
        file
    )
}

// Runs ML Kit text recognition on the captured image (on-device, no internet).
private fun runTextRecognition(
    context: Context,
    uri: Uri,
    onResult: (String) -> Unit,
    onError: (String) -> Unit
) {
    try {
        // Load the photo into an ML Kit InputImage.
        val image = InputImage.fromFilePath(context, uri)
        // DEFAULT_OPTIONS = Latin-script recognizer (English etc.), bundled on-device.
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
        recognizer.process(image)
            .addOnSuccessListener { visionText -> onResult(visionText.text) }
            .addOnFailureListener { e -> onError(e.message ?: "recognition failed") }
    } catch (e: Exception) {
        onError(e.message ?: "could not load image")
    }
}

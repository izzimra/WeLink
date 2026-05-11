# WeLink

> A student-to-student knowledge sharing platform for universities, built in Jetpack Compose.

Aligned with **UN SDG 4 — Quality Education**.

---

## Problem Statement

University students struggle to find study materials like past year papers, notes, and lab reports. These resources are scattered across group chats and personal drives with no central place to find them.

**WeLink** solves this by letting students post and request materials from their own faculty, and earn **XP** for every contribution. Contribution is gamified through a leaderboard so that sharing knowledge becomes rewarding, not an afterthought.

## Alignment with SDG 4 — Quality Education

SDG 4 calls for **inclusive and equitable quality education** and lifelong learning opportunities for all. WeLink contributes to this goal by:

- **Democratizing access** to learning materials across a faculty — no more gatekept Drive folders
- **Crowdsourcing quality** — students share the notes, past year papers, and lab reports they found most useful
- **Incentivizing contribution** through an XP and leaderboard system so learners are rewarded for helping peers
- **Strengthening faculty communities** by scoping materials to each faculty (FTSM, FKAB, etc.)

---

## Features

| Screen | What it does |
| --- | --- |
| **Home** | Greeting card, search bar, faculty cards, material categories (Past Year, Notes, Lab Report, Mock Quiz, Tutorial, Slides, Code, Groups), Student Requests feed, and expandable contribution heat map |
| **Post** | Share a material — pick a type (Past Year / Notes / Lab Report / Tutorial / Mock Quiz), enter a title and course code, and submit. Can also be opened pre-filled from a student request |
| **Request Detail** | View the full details of a request (title, course code, requester, faculty, XP reward) and respond by posting the material |
| **Profile** | Your stats (posts count, XP earned, activity this month) and a live list of the materials you've shared |
| **Leaderboard** | Top contributors this month ranked by XP |

### Key interactions

- Tap a **Student Request** on Home → opens **Request Detail** → **Post This Material** → Post screen pre-filled with the request's title and course code
- Submitting a post adds it live to your **Profile** (via a shared `ViewModel`)
- XP is awarded per contribution (default 80 XP per post, higher for fulfilling specific requests)

---

## Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose + Material 3
- **Architecture:** Single-Activity + `NavHost` navigation, `ViewModel` with `StateFlow` for shared state
- **Min SDK:** 24 • **Target SDK:** 36
- **Build:** Gradle (Kotlin DSL)

### Key Libraries

- `androidx.compose.material3`
- `androidx.navigation:navigation-compose`
- `androidx.lifecycle:lifecycle-viewmodel-compose`
- `androidx.compose.material:material-icons-core`

---

## Project Structure

```
app/src/main/java/com/example/a207944_izzi_izwan_lab02/
├── MainActivity.kt           # Entry point + NavHost routing
├── AppViewModel.kt           # Shared state (list of posts) via StateFlow
├── UserData.kt               # Data models: UserData, MaterialPost
├── Components.kt             # Reusable Composables (StatItem, CategoryItem, RequestRow, etc.)
├── HomeScreen.kt             # Home feed with search, categories, requests
├── PostScreen.kt             # Create a post (standalone or from a request)
├── RequestDetailScreen.kt    # View a student request and respond
├── ProfileScreen.kt          # Personal stats and posts
├── LeaderboardScreen.kt      # Top contributors by XP
└── ui/theme/                 # Color, Theme, Typography
```

### Navigation graph

```
home
 ├── post
 ├── post_from_request/{title}/{courseCode}
 ├── profile
 ├── leaderboard
 └── request_detail/{title}/{courseCode}/{requester}
```

---

## Getting Started

### Prerequisites

- Android Studio Hedgehog or later
- JDK 11
- Android SDK 36 installed

### Build & Run

1. Clone the repo
   ```bash
   git clone <repo-url>
   cd A207944_Izzi_Izwan_Lab02
   ```
2. Open the project in Android Studio
3. Let Gradle sync
4. Run on an emulator or device (API 24+)
   ```bash
   ./gradlew installDebug
   ```

### Tests

```bash
./gradlew test                 # unit tests
./gradlew connectedAndroidTest # instrumented tests
```

---

## Gamification Model

- **Every post** awards XP (default 80 XP, configurable per `MaterialPost.xpValue`)
- **Fulfilling a student request** awards a higher XP bounty (up to +150 XP)
- **Monthly leaderboard** ranks contributors within their faculty
- The Profile screen shows **live totals** that update the moment a post is submitted

---

## Author

**Izzi Izwan**
Matric: 207944 · FTSM, UKM Bangi

Course lab project — Mobile Application Development.

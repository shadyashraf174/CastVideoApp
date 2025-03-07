# CastVideoApp

A simple Android application that demonstrates how to cast video content to a Chromecast device using the Google Cast SDK.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
- [Code Structure](#code-structure)
- [Diagram](#diagram)

---

## Overview

The **CastVideoApp** allows users to stream video content from their Android devices to a Chromecast-enabled device. This app leverages the Google Cast SDK to provide seamless integration with Chromecast devices.

This project includes:
- A `MediaRouteButton` for connecting to Chromecast devices.
- Integration with the Google Cast SDK for media streaming.
- Logging for session management and debugging.

---

## Features

- Connect to Chromecast devices via the `MediaRouteButton`.
- Stream video content (MP4 format) to the connected Chromecast device.
- Handle session lifecycle events (start, end, resume, etc.).
- Debugging support for easier development and troubleshooting.

---

## Prerequisites

Before running this project, ensure you have the following:

1. **Android Studio**: Version 4.0 or higher.
2. **SDK Requirements**:
   - Compile SDK: 34
   - Min SDK: 24
   - Target SDK: 34
3. **Chromecast Device**: Ensure you have access to a Chromecast device for testing.
4. **Google Play Services**: Ensure your device has Google Play Services installed and up-to-date.

---

## Setup Instructions

### Step 1: Clone the Repository

Clone this repository to your local machine using the following command:

```bash
git clone https://github.com/shadyashraf174/CastVideoApp.git
```

### Step 2: Open in Android Studio

1. Open Android Studio and select **Open an existing Android Studio project**.
2. Navigate to the cloned repository folder and open it.

### Step 3: Update Dependencies

Ensure that all dependencies are up-to-date by syncing the Gradle files:

1. Open the `build.gradle.kts` file.
2. Sync the project with Gradle files by clicking **File > Sync Project with Gradle Files**.

### Step 4: Configure Chromecast Application ID

Replace the placeholder Chromecast application ID (`CC1AD845`) in the `CastOptionsProvider` class with your actual Chromecast application ID:

```kotlin
return CastOptions.Builder()
    .setReceiverApplicationId("YOUR_APPLICATION_ID")
    .build()
```

You can obtain your application ID by registering your app in the [Google Cast Developer Console](https://cast.google.com/u/0/publish/#/overview).

### Step 5: Run the Application

1. Connect your Android device or start an emulator.
2. Click the **Run** button in Android Studio to build and deploy the app.

---

## Code Structure

### Key Components

1. **MainActivity.kt**
   - The main entry point of the application.
   - Initializes the `CastContext` and sets up the `MediaRouteButton`.
   - Handles session lifecycle events using a `SessionManagerListener`.

2. **CastOptionsProvider.kt**
   - Provides configuration options for the Google Cast SDK.
   - Sets the Chromecast application ID and enables debug logging.

3. **AndroidManifest.xml**
   - Declares necessary permissions (`INTERNET`, `ACCESS_NETWORK_STATE`).
   - Configures metadata for enabling Cast support.
   - Defines the `MainActivity` as the launcher activity.

4. **activity_main.xml**
   - Layout file for the main activity.
   - Contains a `MediaRouteButton` for connecting to Chromecast devices.

5. **build.gradle.kts**
   - Manages project dependencies and build configurations.
   - Includes the Google Cast SDK and other required libraries.


---

## Diagram

### Class Diagram

Below is a class diagram representing the key components of the application:

![image](https://github.com/user-attachments/assets/358a0f6b-d8b1-4429-9bad-21c2ce5ba7de)

---

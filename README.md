# Uber Driver Mock App

A comprehensive Uber Driver application simulator built with Kotlin and Jetpack Compose for testing, development, and demonstration purposes.

## Overview

This project provides a complete simulation of the Uber Driver mobile application experience without requiring external APIs, authentication systems, or real-time location services. The application demonstrates modern Android development practices using Jetpack Compose and Material Design 3 components.

## Features

### Core Functionality
- **Driver Status Management**: Toggle between online and offline states with visual status indicators
- **Ride Request Simulation**: Automatic generation of mock ride requests with realistic passenger data
- **Interactive Map Integration**: OpenStreetMap implementation via WebView with driver and passenger location markers
- **Earnings Tracking**: Real-time display of daily earnings and completed ride statistics
- **Navigation System**: Multi-screen architecture with bottom navigation bar

### User Interface
- **Material Design 3**: Modern UI components following Google's latest design guidelines
- **Responsive Layout**: Optimized for various Android screen sizes and orientations
- **Professional Styling**: Clean, minimalist interface matching Uber's design language
- **Interactive Elements**: Smooth animations and transitions between application states

### Technical Implementation
- **No External Dependencies**: Operates independently without ride-sharing APIs or location services
- **Mock Data Generation**: Randomized passenger information, routes, and pricing for realistic testing
- **WebView Integration**: Embedded map functionality using OpenStreetMap tiles
- **State Management**: Efficient handling of application state using Compose state management

## Technology Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Design System**: Material Design 3
- **Build System**: Gradle
- **Map Implementation**: OpenStreetMap via WebView
- **Development Environment**: Android Studio

## Project Structure

```
app/src/main/
├── assets/
│   ├── map.html              # Leaflet.js map implementation
│   └── map_simple.html       # Fallback CSS-based map
├── java/app/yonki/ubermockapp/
│   ├── MainActivity.kt       # Application entry point
│   └── ui/
│       ├── components/
│       │   └── UberBottomBar.kt    # Navigation component
│       ├── screens/
│       │   ├── HomeScreen.kt       # Main driver interface
│       │   ├── RideScreen.kt       # Active ride management
│       │   ├── EarningsScreen.kt   # Financial overview
│       │   └── AccountScreen.kt    # Driver profile
│       └── theme/
│           └── Theme.kt            # Material Design theming
└── NewRideModal.kt           # Ride request interface
```

## Installation and Setup

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK API level 21 or higher
- Kotlin compiler version 1.8.0 or later

### Build Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/uber-driver-mock-app.git
   cd uber-driver-mock-app
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned repository directory

3. **Build and Run**
   - Allow Gradle to sync dependencies
   - Connect an Android device or start an emulator
   - Click "Run" or use `Ctrl+R` (Windows/Linux) / `Cmd+R` (macOS)

## Usage Instructions

### Basic Navigation
- **Home Screen**: Primary interface for managing driver status and viewing ride requests
- **Earnings Screen**: Detailed breakdown of daily and weekly earnings
- **Account Screen**: Driver profile information and application settings

### Ride Simulation Flow
1. Set status to "Online" from the Home screen
2. Wait for automatic ride request notifications (every 10 seconds)
3. Accept or decline rides within the 8-second countdown window
4. Navigate to the Ride screen to view active ride details
5. Complete rides and view updated earnings

## Configuration Options

### Map Implementation
The application supports two map rendering methods:
- **Primary**: Leaflet.js with OpenStreetMap tiles (requires internet connectivity)
- **Fallback**: CSS-based static map with animated markers (offline capable)

### Data Simulation
Mock data generation includes:
- Randomized passenger names and ratings
- Realistic São Paulo address locations
- Dynamic pricing based on route simulation
- Variable ride frequency and timing

## Development Notes

### Architecture Decisions
- **Single Activity Architecture**: Utilizes Navigation Compose for screen management
- **Unidirectional Data Flow**: Implements recommended Compose state management patterns
- **Separation of Concerns**: Clear distinction between UI components and business logic

### Performance Considerations
- **Lazy Loading**: Efficient rendering of list components
- **State Optimization**: Minimal recomposition through proper state management
- **Memory Management**: Proper lifecycle handling for WebView components

## Testing and Quality Assurance

### Supported Testing Scenarios
- Driver onboarding simulation
- Ride acceptance/rejection workflows
- Earnings calculation accuracy
- Navigation flow validation
- Map rendering performance
- UI component responsiveness

### Known Limitations
- No real GPS integration
- Simplified route calculation
- Mock passenger data only
- No backend data persistence

## License

This project is provided for educational and testing purposes. It is not affiliated with Uber Technologies Inc. and should not be used for commercial ride-sharing operations.

## Contributing

This project serves as a demonstration and testing tool. Contributions focusing on UI improvements, code optimization, or additional simulation features are welcome through standard pull request procedures.

## Support and Documentation

For technical issues or implementation questions, refer to the Android Developer documentation for Jetpack Compose and Material Design 3 guidelines. Additional resources can be found in the official Kotlin documentation.

# Fuel Price Monitor

A Java-based application built with **Spring Boot** that monitors fuel prices and sends SMS notifications when price changes are detected. The app uses **Jsoup** for web scraping to retrieve fuel price data and **Twilio** to send SMS alerts to users.

---

## Features

- **Fuel Price Monitoring**: Regularly scrapes a specified website to fetch the latest fuel prices.
- **SMS Notifications**: Sends real-time SMS alerts to users via Twilio when fuel prices change.
- **Customizable Alerts**: Users can configure which fuel types to monitor and set thresholds for notifications.
- **Web Scraping**: Utilizes Jsoup to extract and parse fuel price data from target websites.

---

## Technologies Used

- **Java**: Core programming language.
- **Spring Boot**: Framework for building the application.
- **Jsoup**: Library for web scraping and HTML parsing.
- **Twilio**: API for sending SMS notifications.
- **Maven**: Dependency management and build tool.

---

## Setup Instructions

### Prerequisites

1. **Twilio Account**: Sign up for a [Twilio account](https://www.twilio.com/) and obtain your `ACCOUNT_SID`, `AUTH_TOKEN`, and a Twilio phone number.
2. **Java Development Kit (JDK)**: Ensure JDK 8 or higher is installed.
3. **Maven**: Install Maven for dependency management.

### Configuration

1. Clone the repository:
```bash
git clone https://github.com/your-repo/fuel-price-monitor.git
```

2. Navigate to the project directory:
```bash
cd fuel-price-monitor
```

3. Update environment files with your Twilio credentials and other configurations:
```properties
   TWILIO_ACCOUNT_SID
   TWILIO_AUTH_TOKEN
```

4. Build the project using Maven:
```bash
mvn clean install
```

5. Run the application:
```bash
mvn spring-boot:run
```

---

## Usage

1. **Subscribe to Alerts**: Users can subscribe to fuel price alerts by providing their phone number and selecting the fuel types they want to monitor.
2. **Monitor Prices**: The application will periodically scrape the target website for fuel prices and compare them with the previous values.
3. **Receive SMS Alerts**: If a price change is detected, the app will send an SMS notification to subscribed users.

---

## Future Enhancements

- **Multiple Websites**: Support for scraping fuel prices from multiple websites.
- **Email Notifications**: Add email alerts as an alternative to SMS.
- **User Dashboard**: A web interface for users to manage their subscriptions and preferences.
- **Historical Data**: Store and display historical fuel price trends.

---

## Contributing

Contributions are welcome! If you'd like to contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Commit your changes and push to the branch.
4. Submit a pull request with a detailed description of your changes.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

This application is designed to help users stay informed about fuel price changes in real-time, making it easier to plan and save on fuel expenses.
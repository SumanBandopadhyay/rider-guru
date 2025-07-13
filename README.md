# Rider Guru

This project requires external configuration for secrets such as database credentials and API keys. Provide the following environment variables or configuration properties when running the application:

- `DB_URL` – JDBC connection URL for the database
- `DB_USERNAME` – database user name
- `DB_PASSWORD` – database password
- `RABBITMQ_USERNAME` – RabbitMQ user name
- `RABBITMQ_PASSWORD` – RabbitMQ password
- `GOOGLE_MAP_KEY` – Google Maps API key
- `RAZORPAY_API_KEY` – Razorpay API key
- `RAZORPAY_API_SECRET` – Razorpay API secret

These values can be supplied as environment variables, command line arguments, or in an external `application.properties` file.

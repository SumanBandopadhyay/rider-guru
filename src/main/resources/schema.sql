create table if not exists users (
  id serial primary key not null,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(45) NOT NULL,
  is_email_verified TINYINT NOT NULL,
  mobile_number VARCHAR(15) NOT NULL,
  dob DATE NOT NULL,
  profile_image VARCHAR(100) NULL,
  sos_emergency_contact VARCHAR(15) NULL,
  is_active BINARY NOT NULL,
  is_premium BINARY NOT NULL
);

create table if not exists trips (
    id serial primary key not null,
    title varchar(50) not null,
    start_point_name varchar(45) NOT NULL,
    start_loc_map varchar(45) NOT NULL,
    end_point_name varchar(45) NOT NULL,
    end_loc_map varchar(45) NOT NULL,
    scheduled_start_time DATETIME NOT NULL,
    actual_start_time DATETIME NULL,
    scheduled_end_time DATETIME NOT NULL,
    actual_end_time DATETIME NOT NULL,
    is_active BINARY NOT NULL
);

create table if not exists itinerary (
  id serial primary key not null,
  event_description VARCHAR(45) NOT NULL,
  nodal_point_name VARCHAR(45) NOT NULL,
  nodal_point_loc_map VARCHAR(45) NOT NULL,
  nodal_point_scheduled_time DATETIME NOT NULL,
  nodal_point_actual_time DATETIME NULL,
  banner VARCHAR(45) NOT NULL,
  is_active BINARY NOT NULL,
  trip_id bigint unsigned NOT NULL
);

create table if not exists joining_points (
  id serial primary key not null,
  joining_point_name VARCHAR(45) NOT NULL,
  joining_point_loc_map VARCHAR(45) NOT NULL,
  scheduled_joining_time DATETIME NOT NULL,
  actual_joining_time DATETIME NULL,
  is_active BINARY NOT NULL,
  trip_id bigint unsigned NOT NULL
);

create table if not exists notifications (
  id serial primary key not null
);

CREATE TABLE if not exists payments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY not null,
    user_id BIGINT,
    amount INT,
    currency VARCHAR(255),
    accept_partial BOOLEAN,
    first_min_partial_amount INT,
    expire_by BIGINT,
    reference_id VARCHAR(255),
    description TEXT,
    customer_name VARCHAR(255),
    customer_contact VARCHAR(255),
    customer_email VARCHAR(255),
    notify_sms BOOLEAN,
    notify_email BOOLEAN,
    reminder_enable BOOLEAN,
    callback_url VARCHAR(255),
    callback_method VARCHAR(255),
    amount_paid INT,
    created_at BIGINT,
    cancelled_at BIGINT,
    expired_at BIGINT,
    payment_link_id VARCHAR(255),
    short_url VARCHAR(255),
    status VARCHAR(255),
    updated_at BIGINT,
    upi_link BOOLEAN,
    whatsapp_link BOOLEAN,
    razorpay_id VARCHAR(255)
);

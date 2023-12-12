CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50),
    manager VARCHAR(50),
    email VARCHAR(100) NOT NULL UNIQUE,
    work_start_hour TIME DEFAULT '09:00:00',
    work_end_hour TIME DEFAULT '17:00:00',
    password text not null
);
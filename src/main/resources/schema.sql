DROP TABLE IF EXISTS Tasks;
CREATE TABLE Tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    completed BOOLEAN NOT NULL DEFAULT False
);
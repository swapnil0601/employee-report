CREATE TABLE activities(
    id INT PRIMARY KEY AUTO_INCREMENT,
    category VARCHAR(50) NOT NULL,
    minutes INT NOT NULL,
    form_id INT NOT NULL,
    FOREIGN KEY(form_id) REFERENCES forms(id) ON DELETE CASCADE
);
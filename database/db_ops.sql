CREATE TABLE Employee (
    emp_id INT AUTO_INCREMENT PRIMARY KEY,
    emp_name VARCHAR(100) NOT NULL,
    emp_dept VARCHAR(100) NOT NULL
);

CREATE TABLE Access_Card (
    card_id INT AUTO_INCREMENT PRIMARY KEY,
    active BOOLEAN NOT NULL DEFAULT FALSE,
    issues BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE Employee_Access_Card (
    record_id INT AUTO_INCREMENT PRIMARY KEY,
    emp_id INT NOT NULL,
    card_id INT NOT NULL,
    issued_dt DATETIME NOT NULL,
    return_dt DATETIME DEFAULT NULL,
    FOREIGN KEY (emp_id) REFERENCES Employee(emp_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (card_id) REFERENCES Access_Card(card_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT chk_return_dt CHECK (return_dt IS NULL OR return_dt > issued_dt)
);

DELIMITER //

CREATE TRIGGER trg_return_dt_update
BEFORE UPDATE ON Employee_Access_Card
FOR EACH ROW
BEGIN
    -- Prevent updating return_dt if it is already set
    IF OLD.return_dt IS NOT NULL AND NEW.return_dt != OLD.return_dt THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'return_dt can only be updated once.';
    END IF;

    -- Ensure return_dt is greater than issued_dt
    IF NEW.return_dt IS NOT NULL AND NEW.return_dt <= NEW.issued_dt THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'return_dt must be greater than issued_dt.';
    END IF;
END;
//

DELIMITER ;

-- Issue a card to an employee
INSERT INTO Employee_Access_Card (emp_id, card_id, issued_dt)
VALUES (?, ?, NOW());

-- Mark the card as active and issued in the `Access_Card` table
UPDATE Access_Card
SET active = TRUE, issues = TRUE
WHERE card_id = ?;

-- return a specific card for an employee
UPDATE Employee_Access_Card
SET return_dt = NOW()
WHERE emp_id = ? AND card_id = ? AND return_dt IS NULL;

-- Mark the card as inactive in the `Access_Card` table
UPDATE Access_Card
SET active = FALSE
WHERE card_id = ?;

-- return all active cards for an employee
UPDATE Employee_Access_Card
SET return_dt = NOW()
WHERE emp_id = ? AND return_dt IS NULL;

-- Mark all cards as inactive in the `Access_Card` table
UPDATE Access_Card
SET active = FALSE
WHERE card_id IN (
    SELECT card_id
    FROM Employee_Access_Card
    WHERE emp_id = ? AND return_dt IS NOT NULL
);






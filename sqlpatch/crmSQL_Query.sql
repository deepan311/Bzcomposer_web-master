-- RENAME YOUR DATABASE NAME
use bca;
-- create Serviceman 
CREATE TABLE Serviceman (
    serviceman_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(20) NOT NULL,
    password VARCHAR(255) NOT NULL,
    field_of_work VARCHAR(255),
    address TEXT,
    company_id INT,
    third_party BOOLEAN DEFAULT FALSE,
    tax_form_1099 BOOLEAN DEFAULT FALSE,
    status VARCHAR(50) DEFAULT 'Active',
    behavior_status VARCHAR(50) DEFAULT 'Good',
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (company_id) REFERENCES bca_company(CompanyID) ON DELETE SET NULL
);

-- create servicemangroup-- 

CREATE TABLE serviceman_group (
    uid INT PRIMARY KEY AUTO_INCREMENT,
    group_id INT NOT NULL,
    serviceman_id INT NOT NULL,
    lead_serviceman_id INT NOT NULL,
    group_name VARCHAR(255) NOT NULL,
    under_contractor_id INT NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    -- Foreign Keys
    CONSTRAINT fk_serviceman FOREIGN KEY (serviceman_id) REFERENCES serviceman(serviceman_id) ON DELETE CASCADE,
    CONSTRAINT fk_lead_serviceman FOREIGN KEY (lead_serviceman_id) REFERENCES serviceman(serviceman_id) ON DELETE CASCADE,
    CONSTRAINT fk_contractor FOREIGN KEY (under_contractor_id) REFERENCES bca_user(ID) ON DELETE CASCADE
    
);






-- create serviceman_under_contractor

CREATE TABLE serviceman_under_contractor (
    uid INT PRIMARY KEY AUTO_INCREMENT,
    serviceman_id INT NOT NULL,
    contractor_id INT NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    -- Foreign Keys
    FOREIGN KEY (serviceman_id) REFERENCES serviceman(serviceman_id) ON DELETE CASCADE,
	FOREIGN KEY (contractor_id) REFERENCES bca_user(ID) ON DELETE CASCADE
);

ALTER TABLE serviceman_under_contractor 
ADD CONSTRAINT fk_serviceman_serviceman_under_contractor FOREIGN KEY (serviceman_id) REFERENCES serviceman(serviceman_id) ON DELETE CASCADE;

ALTER TABLE serviceman_under_contractor 
ADD CONSTRAINT fk_contractor_serviceman_under_contractor FOREIGN KEY (contractor_id) REFERENCES bca_user(ID) ON DELETE CASCADE;

-- create Customer Job
CREATE TABLE customer_job (
    job_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    contractor_id INT NOT NULL,
    job_name VARCHAR(255) NOT NULL,
    address TEXT NOT NULL,
    ongoing_id INT DEFAULT NULL,
    job_history INT DEFAULT NULL,
    
    job_status ENUM('pending', 'ongoing', 'completed', 'canceled') NOT NULL DEFAULT 'pending',
    invoice_id INT DEFAULT NULL,
    payment_status ENUM('pending', 'completed', 'canceled') NOT NULL DEFAULT 'pending',

    request_date TIMESTAMP default NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    -- Foreign Keys (without explicit CONSTRAINT names)
    FOREIGN KEY (contractor_id) REFERENCES bca_user(ID) ON DELETE CASCADE
    -- job History
    -- invoice_id
    -- ongoing
    -- 
    -- create Remaing constrain Below
);


-- CReate Invoice-- 
CREATE TABLE Job_Invoice (
    invoice_id INT PRIMARY KEY AUTO_INCREMENT,
    job_history TEXT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    tax_amount DECIMAL(10,2) NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


-- create a JOB History

CREATE TABLE Job_History (
    history_id INT PRIMARY KEY AUTO_INCREMENT,
    job_id INT NOT NULL,
    `group` BOOLEAN NOT NULL DEFAULT FALSE, 
    serviceman_id INT DEFAULT NULL,
    group_id INT DEFAULT NULL,
    start_date TIMESTAMP DEFAULT NULL,
    end_date TIMESTAMP DEFAULT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- Foreign Keys
  constraint fk_customer_job_Job_History  FOREIGN KEY (job_id) REFERENCES customer_job(job_id) ON DELETE CASCADE,
  constraint serviceman_id_job_Job_History  FOREIGN KEY (serviceman_id) REFERENCES serviceman(serviceman_id) ON DELETE SET NULL,
   constraint serviceman_id_job_serviceman_group FOREIGN KEY (group_id) REFERENCES serviceman_group(uid) ON DELETE SET NULL
);


-- OnGoing Job table

CREATE TABLE OnGoing_Job (
    ongoing_id INT PRIMARY KEY AUTO_INCREMENT,
    job_id INT NOT NULL,
    `group` BOOLEAN NOT NULL DEFAULT FALSE, 
    group_id INT DEFAULT NULL,
    serviceman_id INT DEFAULT NULL,
    current_status ENUM('request', 'scheduled', 'ongoing', 'completed', 'canceled') NOT NULL DEFAULT 'request',
    schedule_date TIMESTAMP DEFAULT NULL,
    estimate_deadline TIMESTAMP DEFAULT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    -- Foreign Key Constraints
    CONSTRAINT fk_ongoing_job_OnGoing_Job FOREIGN KEY (job_id) REFERENCES customer_job(job_id) ON DELETE CASCADE,
    CONSTRAINT fk_ongoing_group_OnGoing_Job FOREIGN KEY (group_id) REFERENCES serviceman_group(uid) ON DELETE SET NULL,
    CONSTRAINT fk_ongoing_serviceman_OnGoing_Job FOREIGN KEY (serviceman_id) REFERENCES serviceman(serviceman_id) ON DELETE SET NULL
);


-- customer Job Add FK

-- Add Foreign Key for customer_id

-- later ---

-- Add Foreign Key for ongoing_id
ALTER TABLE customer_job 
ADD CONSTRAINT fk_ongoing FOREIGN KEY (ongoing_id) 
REFERENCES OnGoing_Job(ongoing_id) ON DELETE SET NULL;

-- Add Foreign Key for job_history
ALTER TABLE customer_job 
ADD CONSTRAINT fk_job_history FOREIGN KEY (job_history) 
REFERENCES Job_History(history_id) ON DELETE SET NULL;

-- Add Foreign Key for invoice_id
ALTER TABLE customer_job 
ADD CONSTRAINT fk_invoice FOREIGN KEY (invoice_id) 
REFERENCES Job_Invoice(invoice_id) ON DELETE SET NULL;








-- Insert Dummy Data Every Table

-- Insert into Serviceman
INSERT INTO Serviceman ( name, email, phone, password, field_of_work, company_id) VALUES
( 'Mike Serviceman', 'mike.serviceman@example.com', '1234567890', 'password123', 'Electrician', 1),
( 'Sara Serviceman', 'sara.serviceman@example.com', '0987654321', 'password456', 'Plumber', 2);

-- Insert into Serviceman_Group
INSERT INTO serviceman_group (group_id, serviceman_id, lead_serviceman_id, group_name, under_contractor_id) VALUES
(101, 1, 1, 'Electricians Team', 1),
(102, 2, 2, 'Plumbers Team', 2);

-- Insert into Serviceman_Under_Contractor
INSERT INTO serviceman_under_contractor (serviceman_id, contractor_id) VALUES
(1, 1),
(2, 2);

-- Insert into Customer_Job
INSERT INTO customer_job (customer_id, contractor_id, job_name, address) VALUES
(1, 1, 'Electrical Wiring', '123 Street, City'),
(2, 2, 'Plumbing Repair', '456 Avenue, City');

-- Insert into Job_Invoice
INSERT INTO Job_Invoice (job_history, amount, tax_amount) VALUES
('Job 1 completed', 500.00, 50.00),
('Job 2 completed', 700.00, 70.00);

-- Insert into Job_History
INSERT INTO Job_History (job_id, `group`, serviceman_id, group_id, start_date, end_date) VALUES
(1, FALSE, 1, NULL, NOW(), NOW()),
(2, TRUE, NULL, 1, NOW(), NOW());

-- Insert into OnGoing_Job
INSERT INTO OnGoing_Job (job_id, `group`, group_id, serviceman_id, current_status, schedule_date, estimate_deadline) VALUES
(1, FALSE, NULL, 1, 'scheduled', NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY)),
(2, TRUE, 1, NULL, 'ongoing', NOW(), DATE_ADD(NOW(), INTERVAL 14 DAY));



 










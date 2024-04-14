/*Adding Branch Data*/

INSERT INTO branch (brach_id, branch_name, branch_address, branch_contact, branch_fax, branch_email, branch_description, branch_status, branch_location, branch_created_on, branch_created_by)
VALUES
    (1, 'Branch A', '123 Main St, City A', '123-456-7890', '123-456-7891', 'branchA@example.com', 'Description for Branch A', true, 'Location A', NOW(), 'Admin'),
    (2, 'Branch B', '456 Elm St, City B', '234-567-8901', '234-567-8902', 'branchB@example.com', 'Description for Branch B', true, 'Location B', NOW(), 'Admin'),
    (3, 'Branch C', '789 Oak St, City C', '345-678-9012', '345-678-9013', 'branchC@example.com', 'Description for Branch C', true, 'Location C', NOW(), 'Admin'),
    (4, 'Branch D', '101 Pine St, City D', '456-789-0123', '456-789-0124', 'branchD@example.com', 'Description for Branch D', true, 'Location D', NOW(), 'Admin'),
    (5, 'Branch E', '202 Maple St, City E', '567-890-1234', '567-890-1235', 'branchE@example.com', 'Description for Branch E', true, 'Location E', NOW(), 'Admin'),
    (6, 'Branch F', '303 Cedar St, City F', '678-901-2345', '678-901-2346', 'branchF@example.com', 'Description for Branch F', true, 'Location F', NOW(), 'Admin'),
    (7, 'Branch G', '404 Walnut St, City G', '789-012-3456', '789-012-3457', 'branchG@example.com', 'Description for Branch G', true, 'Location G', NOW(), 'Admin'),
    (8, 'Branch H', '505 Birch St, City H', '890-123-4567', '890-123-4568', 'branchH@example.com', 'Description for Branch H', true, 'Location H', NOW(), 'Admin'),
    (9, 'Branch I', '606 Pine St, City I', '901-234-5678', '901-234-5679', 'branchI@example.com', 'Description for Branch I', true, 'Location I', NOW(), 'Admin'),
    (10, 'Branch J', '707 Oak St, City J', '012-345-6789', '012-345-6790', 'branchJ@example.com', 'Description for Branch J', true, 'Location J', NOW(), 'Admin');

/*Employer bank details*/

INSERT INTO employer_bankdetails (employer_bank_details_id, bank_name, bank_branch_name, bank_account_number, employer_description, cashier_monthly_payment, payment_status, employeer_id)
VALUES
    (1, 'Bank A', 'Branch A', '1234567890', 'Description for Employer 1', 2000.00, true, 1),
    (2, 'Bank B', 'Branch B', '2345678901', 'Description for Employer 2', 2100.00, false, 2),
    (3, 'Bank C', 'Branch C', '3456789012', 'Description for Employer 3', 2200.00, true, 3),
    (4, 'Bank D', 'Branch D', '4567890123', 'Description for Employer 4', 2300.00, false, 4),
    (5, 'Bank E', 'Branch E', '5678901234', 'Description for Employer 5', 2400.00, true, 5),
    (6, 'Bank F', 'Branch F', '6789012345', 'Description for Employer 6', 2500.00, false, 6),
    (7, 'Bank G', 'Branch G', '7890123456', 'Description for Employer 7', 2600.00, true, 7),
    (8, 'Bank H', 'Branch H', '8901234567', 'Description for Employer 8', 2700.00, false, 8),
    (9, 'Bank I', 'Branch I', '9012345678', 'Description for Employer 9', 2800.00, true, 9),
    (10, 'Bank J', 'Branch J', '0123456789', 'Description for Employer 10', 2900.00, false, 10),
    (11, 'Bank A', 'Branch A', '1234567890', 'Description for Employer 11', 2000.00, true, 11),
    (12, 'Bank B', 'Branch B', '2345678901', 'Description for Employer 12', 2100.00, false, 12),
    (13, 'Bank C', 'Branch C', '3456789012', 'Description for Employer 13', 2200.00, true, 13),
    (14, 'Bank D', 'Branch D', '4567890123', 'Description for Employer 14', 2300.00, false, 14),
    (15, 'Bank E', 'Branch E', '5678901234', 'Description for Employer 15', 2400.00, true, 15),
    (16, 'Bank F', 'Branch F', '6789012345', 'Description for Employer 16', 2500.00, false, 16),
    (17, 'Bank G', 'Branch G', '7890123456', 'Description for Employer 17', 2600.00, true, 17),
    (18, 'Bank H', 'Branch H', '8901234567', 'Description for Employer 18', 2700.00, false, 18),
    (19, 'Bank I', 'Branch I', '9012345678', 'Description for Employer 19', 2800.00, true, 19),
    (20, 'Bank J', 'Branch J', '0123456789', 'Description for Employer 20', 2900.00, false, 20);


/*Employee data*/

INSERT INTO employer (employer_id, employer_nic_name, employer_first_name, employer_last_name, profile_image, employer_password, employer_email, employer_phone, employer_address, employer_sallary, employer_nic, is_active, pin, gender, employer_date_of_birth, role, employer_bank_details_id, brach_id)
VALUES
    (1, 'NicName1', 'John', 'Doe', NULL, 'password1', 'john@example.com', '123-456-7890', '123 Main St', 2500.00, '123456789012', true, 1234, 'MALE', '1990-01-01', 'CASHIER', 1, 1),
    (2, 'NicName2', 'Jane', 'Smith', NULL, 'password2', 'jane@example.com', '234-567-8901', '456 Elm St', 2800.00, '234567890123', true, 2345, 'FEMALE', '1991-02-02', 'MANAGER', 2, 2),
    (3, 'NicName3', 'Michael', 'Johnson', NULL, 'password3', 'michael@example.com', '345-678-9012', '789 Oak St', 3000.00, '345678901234', true, 3456, 'MALE', '1992-03-03', 'CASHIER', 3, 3),
    (4, 'NicName4', 'Emily', 'Williams', NULL, 'password4', 'emily@example.com', '456-789-0123', '101 Pine St', 3200.00, '456789012345', true, 4567, 'FEMALE', '1993-04-04', 'CASHIER', 4, 4),
    (5, 'NicName5', 'David', 'Brown', NULL, 'password5', 'david@example.com', '567-890-1234', '202 Maple St', 3400.00, '567890123456', true, 5678, 'MALE', '1994-05-05', 'CASHIER', 5, 5),
    (6, 'NicName6', 'Sarah', 'Jones', NULL, 'password6', 'sarah@example.com', '678-901-2345', '303 Cedar St', 3600.00, '678901234567', true, 6789, 'FEMALE', '1995-06-06', 'MANAGER', 6, 6),
    (7, 'NicName7', 'Christopher', 'Martinez', NULL, 'password7', 'christopher@example.com', '789-012-3456', '404 Walnut St', 3800.00, '789012345678', true, 7890, 'FEMALE', '1996-07-07', 'CASHIER', 7, 7),
    (8, 'NicName8', 'Amanda', 'Garcia', NULL, 'password8', 'amanda@example.com', '890-123-4567', '505 Birch St', 4000.00, '890123456789', true, 8901, 'FEMALE', '1997-08-08', 'OTHER', 8, 8),
    (9, 'NicName9', 'James', 'Rodriguez', NULL, 'password9', 'james@example.com', '901-234-5678', '606 Pine St', 4200.00, '901234567890', true, 9012, 'MALE', '1998-09-09', 'CASHIER', 9, 9),
    (10, 'NicName10', 'Jessica', 'Hernandez', NULL, 'password10', 'jessica@example.com', '012-345-6789', '707 Oak St', 4400.00, '012345678901', true, 1234, 'FEMALE', '1999-10-10', 'MANAGER', 10, 10),
    (11, 'NicName11', 'Matthew', 'Lopez', NULL, 'password11', 'matthew@example.com', '123-456-7890', '123 Main St', 2500.00, '123456789012', true, 2345, 'MALE', '1990-01-01', 'CASHIER', 11, 1),
    (12, 'NicName12', 'Ashley', 'Gonzalez', NULL, 'password12', 'ashley@example.com', '234-567-8901', '456 Elm St', 2800.00, '234567890123', true, 3456, 'FEMALE', '1991-02-02', 'CASHIER', 12, 2),
    (13, 'NicName13', 'Daniel', 'Wilson', NULL, 'password13', 'daniel@example.com', '345-678-9012', '789 Oak St', 3000.00, '345678901234', true, 4567, 'MALE', '1992-03-03', 'CASHIER', 13, 3),
    (14, 'NicName14', 'Brittany', 'Taylor', NULL, 'password14', 'brittany@example.com', '456-789-0123', '101 Pine St', 3200.00, '456789012345', true, 5678, 'FEMALE', '1993-04-04', 'OWNER', 14, 4),
    (15, 'NicName15', 'Andrew', 'Lee', NULL, 'password15', 'andrew@example.com', '567-890-1234', '202 Maple St', 3400.00, '567890123456', true, 6789, 'MALE', '1994-05-05', 'CASHIER', 15, 5),
    (16, 'NicName16', 'Stephanie', 'Martinez', NULL, 'password16', 'stephanie@example.com', '678-901-2345', '303 Cedar St', 3600.00, '678901234567', true, 7890, 'FEMALE', '1995-06-06', 'MANAGER', 16, 6),
    (17, 'NicName17', 'Ryan', 'Hernandez', NULL, 'password17', 'ryan@example.com', '789-012-3456', '404 Walnut St', 3800.00, '789012345678', true, 8901, 'MALE', '1996-07-07', 'CASHIER', 17, 7),
    (18, 'NicName18', 'Lauren', 'Young', NULL, 'password18', 'lauren@example.com', '890-123-4567', '505 Birch St', 4000.00, '890123456789', true, 9012, 'FEMALE', '1997-08-08', 'MANAGER', 18, 8),
    (19, 'NicName19', 'Kevin', 'King', NULL, 'password19', 'kevin@example.com', '901-234-5678', '606 Pine St', 4200.00, '901234567890', true, 1234, 'MALE', '1998-09-09', 'MANAGER', 19, 9),
    (20, 'NicName20', 'Megan', 'Wright', NULL, 'password20', 'megan@example.com', '012-345-6789', '707 Oak St', 4400.00, '012345678901', true, 2345, 'FEMALE', '1999-10-10', 'CASHIER', 20, 10);

INSERT INTO item_category (category_id, category_name, category_description, category_image)
VALUES
    (1, 'Prescription Medications', 'Category for prescription drugs', 'prescription_image.jpg'),
    (2, 'Over-the-Counter Medications', 'Category for over-the-counter drugs', 'otc_image.jpg'),
    (3, 'Personal Care Products', 'Category for personal care items', 'personal_care_image.jpg'),
    (4, 'Vitamins and Supplements', 'Category for vitamins and dietary supplements', 'vitamins_image.jpg'),
    (5, 'First Aid Supplies', 'Category for first aid supplies', 'first_aid_image.jpg'),
    (6, 'Health Monitoring Devices', 'Category for health monitoring devices', 'health_monitoring_image.jpg'),
    (7, 'Baby Care Products', 'Category for baby care items', 'baby_care_image.jpg'),
    (8, 'Beauty Products', 'Category for beauty and skincare products', 'beauty_products_image.jpg'),
    (9, 'Medical Equipment', 'Category for medical equipment and supplies', 'medical_equipment_image.jpg'),
    (10, 'Hygiene Essentials', 'Category for hygiene essentials', 'hygiene_essentials_image.jpg');


INSERT INTO supplier_company (company_id, company_name, company_address, company_contact, company_email, company_description, company_image, company_status, company_bank, company_account_number)
VALUES
    (1, 'SupplierCo 1', '123 Main St', '123-456-7890', 'supplier1@example.com', 'Description for SupplierCo 1', 'image1.jpg', 'Active', 'Bank A', '1234567890'),
    (2, 'SupplierCo 2', '456 Elm St', '234-567-8901', 'supplier2@example.com', 'Description for SupplierCo 2', 'image2.jpg', 'Inactive', 'Bank B', '2345678901'),
    (3, 'SupplierCo 3', '789 Oak St', '345-678-9012', 'supplier3@example.com', 'Description for SupplierCo 3', 'image3.jpg', 'Active', 'Bank C', '3456789012'),
    (4, 'SupplierCo 4', '101 Pine St', '456-789-0123', 'supplier4@example.com', 'Description for SupplierCo 4', 'image4.jpg', 'Active', 'Bank D', '4567890123'),
    (5, 'SupplierCo 5', '202 Maple St', '567-890-1234', 'supplier5@example.com', 'Description for SupplierCo 5', 'image5.jpg', 'Inactive', 'Bank E', '5678901234'),
    (6, 'SupplierCo 6', '303 Cedar St', '678-901-2345', 'supplier6@example.com', 'Description for SupplierCo 6', 'image6.jpg', 'Active', 'Bank F', '6789012345'),
    (7, 'SupplierCo 7', '404 Walnut St', '789-012-3456', 'supplier7@example.com', 'Description for SupplierCo 7', 'image7.jpg', 'Active', 'Bank G', '7890123456'),
    (8, 'SupplierCo 8', '505 Birch St', '890-123-4567', 'supplier8@example.com', 'Description for SupplierCo 8', 'image8.jpg', 'Inactive', 'Bank H', '8901234567'),
    (9, 'SupplierCo 9', '606 Pine St', '901-234-5678', 'supplier9@example.com', 'Description for SupplierCo 9', 'image9.jpg', 'Active', 'Bank I', '9012345678'),
    (10, 'SupplierCo 10', '707 Oak St', '012-345-6789', 'supplier10@example.com', 'Description for SupplierCo 10', 'image10.jpg', 'Active', 'Bank J', '0123456789');


INSERT INTO supplier (supplier_id, supplier_name, supplier_address, supplier_phone, supplier_email, supplier_description, supplier_image, supplier_rating, company_id, created_at, created_by)
VALUES
    (1, 'Supplier 1', '123 Supplier St', '123-456-7890', 'supplier1@example.com', 'Description for Supplier 1', 'supplier1_image.jpg', '4.5', 1, CURRENT_TIMESTAMP, 'Admin'),
    (2, 'Supplier 2', '456 Supplier St', '234-567-8901', 'supplier2@example.com', 'Description for Supplier 2', 'supplier2_image.jpg', '4.2', 2, CURRENT_TIMESTAMP, 'Admin'),
    (3, 'Supplier 3', '789 Supplier St', '345-678-9012', 'supplier3@example.com', 'Description for Supplier 3', 'supplier3_image.jpg', '4.8', 3, CURRENT_TIMESTAMP, 'Admin'),
    (4, 'Supplier 4', '101 Supplier St', '456-789-0123', 'supplier4@example.com', 'Description for Supplier 4', 'supplier4_image.jpg', '4.6', 4, CURRENT_TIMESTAMP, 'Admin'),
    (5, 'Supplier 5', '202 Supplier St', '567-890-1234', 'supplier5@example.com', 'Description for Supplier 5', 'supplier5_image.jpg', '4.3', 5, CURRENT_TIMESTAMP, 'Admin'),
    (6, 'Supplier 6', '303 Supplier St', '678-901-2345', 'supplier6@example.com', 'Description for Supplier 6', 'supplier6_image.jpg', '4.7', 6, CURRENT_TIMESTAMP, 'Admin'),
    (7, 'Supplier 7', '404 Supplier St', '789-012-3456', 'supplier7@example.com', 'Description for Supplier 7', 'supplier7_image.jpg', '4.9', 7, CURRENT_TIMESTAMP, 'Admin'),
    (8, 'Supplier 8', '505 Supplier St', '890-123-4567', 'supplier8@example.com', 'Description for Supplier 8', 'supplier8_image.jpg', '4.4', 8, CURRENT_TIMESTAMP, 'Admin'),
    (9, 'Supplier 9', '606 Supplier St', '901-234-5678', 'supplier9@example.com', 'Description for Supplier 9', 'supplier9_image.jpg', '4.8', 9, CURRENT_TIMESTAMP, 'Admin'),
    (10, 'Supplier 10', '707 Supplier St', '012-345-6789', 'supplier10@example.com', 'Description for Supplier 10', 'supplier10_image.jpg', '4.6', 10, CURRENT_TIMESTAMP, 'Admin');


INSERT INTO item (item_id, item_name, selling_price, item_barcode, supply_date, supplier_price, is_free_issued, is_discounted, item_Manufacture, item_quantity, is_stock, measuring_unit_type, manufacture_date, expire_date, purchase_date, warranty_period, rack_number, discounted_price, discounted_percentage, warehouse_name, is_special_condition, item_image, item_description, date_created, last_updated_date, category_id, supplier_id)
VALUES
    (1, 'Item 1', 10.99, '123456789012', '2024-04-12', 8.99, false, true, 'Manufacturer A', 100, true, 'PIECE', '2023-01-01', '2025-01-01', '2024-04-12', '1 year', 'A1', 9.99, 10.0, 'Warehouse A', false, 'item1_image.jpg', 'Description for Item 1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1),
    (2, 'Item 2', 15.99, '234567890123', '2024-04-12', 12.99, false, false, 'Manufacturer B', 150, true, 'PIECE', '2023-02-01', '2025-02-01', '2024-04-12', '2 years', 'B2', 13.99, 15.0, 'Warehouse B', false, 'item2_image.jpg', 'Description for Item 2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 2),
    (3, 'Item 3', 20.99, '345678901234', '2024-04-12', 18.99, true, false, 'Manufacturer C', 200, true, 'PIECE', '2023-03-01', '2025-03-01', '2024-04-12', '3 years', 'C3', 19.99, 20.0, 'Warehouse C', false, 'item3_image.jpg', 'Description for Item 3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 3),
    (4, 'Item 4', 25.99, '456789012345', '2024-04-12', 22.99, false, true, 'Manufacturer D', 250, true, 'PIECE', '2023-04-01', '2025-04-01', '2024-04-12', '4 years', 'D4', 23.99, 25.0, 'Warehouse D', false, 'item4_image.jpg', 'Description for Item 4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4),
    (5, 'Item 5', 30.99, '567890123456', '2024-04-12', 28.99, true, false, 'Manufacturer E', 300, true, 'PIECE', '2023-05-01', '2025-05-01', '2024-04-12', '5 years', 'E5', 29.99, 30.0, 'Warehouse E', false, 'item5_image.jpg', 'Description for Item 5', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5),
    (6, 'Item 6', 35.99, '678901234567', '2024-04-12', 32.99, false, true, 'Manufacturer F', 350, true, 'PIECE', '2023-06-01', '2025-06-01', '2024-04-12', '6 years', 'F6', 33.99, 35.0, 'Warehouse F', false, 'item6_image.jpg', 'Description for Item 6', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 2),
    (7, 'Item 7', 40.99, '789012345678', '2024-04-12', 38.99, true, false, 'Manufacturer G', 400, true, 'PIECE', '2023-07-01', '2025-07-01', '2024-04-12', '7 years', 'G7', 39.99, 40.0, 'Warehouse G', false, 'item7_image.jpg', 'Description for Item 7', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 3),
    (8, 'Item 8', 45.99, '890123456789', '2024-04-12', 42.99, false, true, 'Manufacturer H', 450, true, 'PIECE', '2023-08-01', '2025-08-01', '2024-04-12', '8 years', 'H8', 43.99, 45.0, 'Warehouse H', false, 'item8_image.jpg', 'Description for Item 8', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 4),
    (9, 'Item 9', 50.99, '901234567890', '2024-04-12', 48.99, true, false, 'Manufacturer I', 500, true, 'PIECE', '2023-09-01', '2025-09-01', '2024-04-12', '9 years', 'I9', 49.99, 50.0, 'Warehouse I', false, 'item9_image.jpg', 'Description for Item 9', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 5),
    (10, 'Item 10', 55.99, '012345678901', '2024-04-12', 52.99, false, true, 'Manufacturer J', 550, true, 'PIECE', '2023-10-01', '2025-10-01', '2024-04-12', '10 years', 'J10', 53.99, 55.0, 'Warehouse J', false, 'item10_image.jpg', 'Description for Item 10', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 1);

INSERT INTO authorities (id,name, employer_id) VALUES
                                                (1,'ROLE_CASHIER', 1),
                                                (2,'ROLE_BRANCHMANAGER', 2),
                                                (3,'ROLE_CASHIER', 3),
                                                (4,'ROLE_CASHIER', 4),
                                                (5,'ROLE_CASHIER', 5),
                                                (6,'ROLE_BRANCHMANAGER', 6),
                                                (8,'ROLE_OTHER', 8),
                                                (9,'ROLE_CASHIER', 9),
                                                (10,'ROLE_BRANCHMANAGER', 10),
                                                (14,'ROLE_OWNER',14);

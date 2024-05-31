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


INSERT INTO item (
    item_id, branch_id, date_created, discounted_percentage, discounted_price, expire_date, is_discounted, is_free_issued, is_special_condition, item_barcode, item_description, item_image, item_manufacture, item_name, item_quantity, last_updated_date, manufacture_date, measuring_unit_type, purchase_date, rack_number, selling_price, is_stock, supplier_price, supply_date, warehouse_name, warranty_period, category_id, supplier_id
) VALUES
      (1, 1, CURRENT_TIMESTAMP, 10.0, 9.99, '2025-01-01', true, false, false, '123456789012', 'Description for Medicine 1', 'medicine1_image.jpg', 'Manufacturer A', 'Panadol', 100, CURRENT_TIMESTAMP, '2023-01-01', 'TABLETS', '2024-04-12', 'A1', 10.99, true, 8.99, '2024-04-12', 'Warehouse A', '1 year', 1, 1),
      (2, 2, CURRENT_TIMESTAMP, 15.0, 13.99, '2025-02-01', true, false, false, '234567890123', 'Description for Medicine 2', 'medicine2_image.jpg', 'Manufacturer B', 'Panadol', 150, CURRENT_TIMESTAMP, '2023-02-01', 'TABLETS', '2024-04-12', 'B2', 15.99, true, 12.99, '2024-04-12', 'Warehouse B', '2 years', 2, 2),
      (3, 3, CURRENT_TIMESTAMP, 20.0, 19.99, '2025-03-01', true, true, false, '345678901234', 'Description for Medicine 3', 'medicine3_image.jpg', 'Manufacturer C', 'VitamineC', 200, CURRENT_TIMESTAMP, '2023-03-01', 'TABLETS', '2024-04-12', 'C3', 20.99, true, 18.99, '2024-04-12', 'Warehouse C', '3 years', 3, 3),
      (4, 4, CURRENT_TIMESTAMP, 25.0, 23.99, '2025-04-01', true, false, false, '456789012345', 'Description for Medicine 4', 'medicine4_image.jpg', 'Manufacturer D', 'Panadol', 250, CURRENT_TIMESTAMP, '2023-04-01', 'TABLETS', '2024-04-12', 'D4', 25.99, true, 22.99, '2024-04-12', 'Warehouse D', '4 years', 4, 4),
      (5, 5, CURRENT_TIMESTAMP, 30.0, 29.99, '2025-05-01', true, true, false, '567890123456', 'Description for Medicine 5', 'medicine5_image.jpg', 'Manufacturer E', 'Medicine5', 300, CURRENT_TIMESTAMP, '2023-05-01', 'TABLETS', '2024-04-12', 'E5', 30.99, true, 28.99, '2024-04-12', 'Warehouse E', '5 years', 5, 5),
      (6, 1, CURRENT_TIMESTAMP, 35.0, 33.99, '2025-06-01', true, false, false, '678901234567', 'Description for Medicine 6', 'medicine6_image.jpg', 'Manufacturer F', 'Medicine6', 350, CURRENT_TIMESTAMP, '2023-06-01', 'TABLETS', '2024-04-12', 'F6', 35.99, true, 32.99, '2024-04-12', 'Warehouse F', '6 years', 1, 2),
      (7, 2, CURRENT_TIMESTAMP, 40.0, 39.99, '2025-07-01', true, true, false, '789012345678', 'Description for Medicine 7', 'medicine7_image.jpg', 'Manufacturer G', 'Medicine7', 400, CURRENT_TIMESTAMP, '2023-07-01', 'TABLETS', '2024-04-12', 'G7', 40.99, true, 38.99, '2024-04-12', 'Warehouse G', '7 years', 2, 3),
      (8, 3, CURRENT_TIMESTAMP, 45.0, 43.99, '2025-08-01', true, false, false, '890123456789', 'Description for Medicine 8', 'medicine8_image.jpg', 'Manufacturer H', 'Medicine8', 450, CURRENT_TIMESTAMP, '2023-08-01', 'TABLETS', '2024-04-12', 'H8', 45.99, true, 42.99, '2024-04-12', 'Warehouse H', '8 years', 3, 4),
      (9, 4, CURRENT_TIMESTAMP, 50.0, 49.99, '2025-09-01', true, true, false, '901234567890', 'Description for Medicine 9', 'medicine9_image.jpg', 'Manufacturer I', 'Panadol', 500, CURRENT_TIMESTAMP, '2023-09-01', 'TABLETS', '2024-04-12', 'I9', 50.99, true, 48.99, '2024-04-12', 'Warehouse I', '9 years', 4, 5),
      (10, 5, CURRENT_TIMESTAMP, 55.0, 53.99, '2025-10-01', true, false, false, '012345678901', 'Description for Medicine 10', 'medicine10_image.jpg', 'Manufacturer J', 'Medicine2', 550, CURRENT_TIMESTAMP, '2023-10-01', 'TABLETS', '2024-04-12', 'J10', 55.99, true, 52.99, '2024-04-12', 'Warehouse J', '10 years', 5, 1),
      (11, 1, CURRENT_TIMESTAMP, 5.0, 4.99, '2025-11-01', true, false, false, '112345678901', 'Description for Medicine 11', 'medicine11_image.jpg', 'Manufacturer K', 'Medicine5', 600, CURRENT_TIMESTAMP, '2023-11-01', 'TABLETS', '2024-04-12', 'K11', 5.99, true, 4.99, '2024-04-12', 'Warehouse K', '11 years', 1, 2),
      (12, 2, CURRENT_TIMESTAMP, 6.0, 5.99, '2025-12-01', true, true, false, '122345678901', 'Description for Medicine 12', 'medicine12_image.jpg', 'Manufacturer L', 'Medicine5', 650, CURRENT_TIMESTAMP, '2023-12-01', 'TABLETS', '2024-04-12', 'L12', 6.99, true, 5.99, '2024-04-12', 'Warehouse L', '12 years', 2, 3),
      (13, 3, CURRENT_TIMESTAMP, 7.0, 6.99, '2026-01-01', true, false, false, '132345678901', 'Description for Medicine 13', 'medicine13_image.jpg', 'Manufacturer M', 'Medicine4', 700, CURRENT_TIMESTAMP, '2024-01-01', 'TABLETS', '2024-04-12', 'M13', 7.99, true, 6.99, '2024-04-12', 'Warehouse M', '13 years', 3, 4),
      (14, 4, CURRENT_TIMESTAMP, 8.0, 7.99, '2026-02-01', true, true, false, '142345678901', 'Description for Medicine 14', 'medicine14_image.jpg', 'Manufacturer N', 'Medicine3', 750, CURRENT_TIMESTAMP, '2024-02-01', 'TABLETS', '2024-04-12', 'N14', 8.99, true, 7.99, '2024-04-12', 'Warehouse N', '14 years', 4, 5),
      (15, 5, CURRENT_TIMESTAMP, 9.0, 8.99, '2026-03-01', true, false, false, '152345678901', 'Description for Medicine 15', 'medicine15_image.jpg', 'Manufacturer O', 'Medicine3', 800, CURRENT_TIMESTAMP, '2024-03-01', 'TABLETS', '2024-04-12', 'O15', 9.99, true, 8.99, '2024-04-12', 'Warehouse O', '15 years', 5, 1),
      (16, 1, CURRENT_TIMESTAMP, 10.0, 9.99, '2026-04-01', true, true, false, '162345678901', 'Description for Medicine 16', 'medicine16_image.jpg', 'Manufacturer P', 'Medicine1', 850, CURRENT_TIMESTAMP, '2024-04-01', 'TABLETS', '2024-04-12', 'P16', 10.99, true, 9.99, '2024-04-12', 'Warehouse P', '16 years', 1, 2),
      (17, 2, CURRENT_TIMESTAMP, 11.0, 10.99, '2026-05-01', true, false, false, '172345678901', 'Description for Medicine 17', 'medicine17_image.jpg', 'Manufacturer Q', 'Medicine1', 900, CURRENT_TIMESTAMP, '2024-05-01', 'TABLETS', '2024-04-12', 'Q17', 11.99, true, 10.99, '2024-04-12', 'Warehouse Q', '17 years', 2, 3),
      (18, 3, CURRENT_TIMESTAMP, 12.0, 11.99, '2026-06-01', true, true, false, '182345678901', 'Description for Medicine 18', 'medicine18_image.jpg', 'Manufacturer R', 'Medicine2', 950, CURRENT_TIMESTAMP, '2024-06-01', 'TABLETS', '2024-04-12', 'R18', 12.99, true, 11.99, '2024-04-12', 'Warehouse R', '18 years', 3, 4),
      (19, 4, CURRENT_TIMESTAMP, 13.0, 12.99, '2026-07-01', true, false, false, '192345678901', 'Description for Medicine 19', 'medicine19_image.jpg', 'Manufacturer S', 'Medicine2', 1000, CURRENT_TIMESTAMP, '2024-07-01', 'TABLETS', '2024-04-12', 'S19', 13.99, true, 12.99, '2024-04-12', 'Warehouse S', '19 years', 4, 5),
      (20, 5, CURRENT_TIMESTAMP, 14.0, 13.99, '2026-08-01', true, true, false, '202345678901', 'Description for Medicine 20', 'medicine20_image.jpg', 'Manufacturer T', 'Medicine2', 1050, CURRENT_TIMESTAMP, '2024-08-01', 'TABLETS', '2024-04-12', 'T20', 14.99, true, 13.99, '2024-04-12', 'Warehouse T', '20 years', 5, 1);

INSERT INTO branch_item(branch_id, item_id) VALUES
    (1, 1),
    (2, 1),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10, 10),
    (1, 2),
    (2, 2),
    (3, 2),
    (4, 2),
    (5, 2),
    (6, 2),
    (7, 2),
    (8, 2),
    (9, 2),
    (10, 2),
    (1, 3),
    (1, 4),
    (3, 4),
    (4, 3),
    (5, 3),
    (6, 3),
    (7, 3),
    (8, 3),
    (9, 3),
    (10, 5);
-- LifePill POS System - Complete Database Seed Script

BEGIN;

-- Temporarily disable Foreign Key checks to handle circular dependencies
SET session_replication_role = 'replica';

-- CLEAN UP EXISTING DATA
DELETE FROM payment_details;
DELETE FROM order_details;
DELETE FROM orders;
DELETE FROM branch_item;
DELETE FROM item;
DELETE FROM item_category;
DELETE FROM employer;
DELETE FROM employer_bankdetails;
DELETE FROM supplier;
DELETE FROM supplier_company;
DELETE FROM branch;

-- 1. SUPPLIER COMPANY TABLE
INSERT INTO supplier_company (company_id, company_name, company_address, company_contact, company_email, company_description, company_image, company_status, company_bank, company_account_number, created_at, created_by, updated_at, updated_by) VALUES
(1, 'PharmaCorp International', '123 Pharma Street, Colombo 01', '0112223344', 'info@pharmacorp.com', 'Leading pharmaceutical distributor in Sri Lanka', null, '5', 'Commercial Bank', '1234567890123', '2024-01-01 00:00:00', 'system', null, null),
(2, 'MediSupply Lanka', '456 Health Road, Kandy', '0815556677', 'sales@medisupply.lk', 'Wholesale medical supplies distributor', null, '4', 'Sampath Bank', '9876543210987', '2024-01-15 00:00:00', 'system', null, null),
(3, 'HealthPlus Distributors', '789 Wellness Ave, Galle', '0917778899', 'contact@healthplus.lk', 'Premium healthcare products supplier', null, '5', 'BOC', '5555666677778', '2024-02-01 00:00:00', 'system', null, null),
(4, 'Global Pharma Solutions', '321 Medical Lane, Negombo', '0319990011', 'orders@globalpharma.com', 'International pharmaceutical imports', null, '4', 'HNB', '1111222233334', '2024-03-01 00:00:00', 'system', null, null);

-- 2. SUPPLIER TABLE
INSERT INTO supplier (supplier_id, supplier_name, supplier_address, supplier_phone, supplier_email, supplier_description, supplier_image, supplier_rating, company_id, created_at, created_by, updated_at, updated_by) VALUES
(1, 'ABC Pharmaceuticals', '100 Lab Street, Colombo 03', '0771234567', 'abc@pharma.lk', 'Primary supplier for analgesics and general medicines', null, '5', 1, '2024-01-01 00:00:00', 'system', null, null),
(2, 'XYZ Medical Supplies', '200 Hospital Road, Colombo 07', '0772345678', 'xyz@medical.lk', 'Antibiotics and specialized medicines supplier', null, '4', 1, '2024-01-15 00:00:00', 'system', null, null),
(3, 'Wellness Products Ltd', '300 Health Park, Kandy', '0773456789', 'wellness@products.lk', 'Vitamins and supplements supplier', null, '5', 2, '2024-02-01 00:00:00', 'system', null, null),
(4, 'CareFirst Pharma', '400 Care Street, Galle', '0774567890', 'carefirst@pharma.lk', 'Cardiovascular medicines specialist', null, '4', 3, '2024-02-15 00:00:00', 'system', null, null),
(5, 'DiabeCare Supplies', '500 Sugar Lane, Colombo 05', '0775678901', 'diabecare@supplies.lk', 'Diabetes care products and medicines', null, '5', 2, '2024-03-01 00:00:00', 'system', null, null),
(6, 'SkinHealth Labs', '600 Derma Road, Nugegoda', '0776789012', 'skinhealth@labs.lk', 'Dermatological products supplier', null, '4', 4, '2024-03-15 00:00:00', 'system', null, null);

-- 3. BRANCH TABLE
INSERT INTO branch (branch_id, branch_name, branch_address, branch_contact, branch_fax, branch_email, branch_description, branch_image, branch_status, branch_location, branch_created_on, branch_created_by, branch_profile_image_url) VALUES
(1, 'Head Office', 'LifePill Building, 123 Main Street, Colombo 03', '0112345678', '0112345679', 'headoffice@lifepill.com', 'Main headquarters and flagship pharmacy branch', null, true, '6.9271,79.8612', '2024-01-01', 'system', null),
(2, 'Maradana Branch', '456 Maradana Road, Colombo 10', '0119876543', '0119876544', 'maradana@lifepill.com', 'High traffic urban branch near Maradana station', null, true, '6.9147,79.8646', '2024-06-01', 'system', null),
(3, 'Kandy City Branch', '789 Dalada Veediya, Kandy', '0812233445', '0812233446', 'kandy@lifepill.com', 'Central province main branch', null, true, '7.2906,80.6337', '2024-07-01', 'system', null),
(4, 'Galle Fort Branch', '321 Church Street, Galle Fort', '0914455667', '0914455668', 'galle@lifepill.com', 'Southern province flagship branch', null, true, '6.0328,80.2168', '2024-08-01', 'system', null),
(5, 'Negombo Beach Branch', '555 Lewis Place, Negombo', '0316677889', null, 'negombo@lifepill.com', 'Coastal area pharmacy serving tourists and locals', null, true, '7.2008,79.8358', '2024-09-01', 'system', null);

-- 4. ITEM CATEGORY TABLE
INSERT INTO item_category (category_id, category_name, category_description, category_image) VALUES
(1, 'Analgesics', 'Pain relief medicines including NSAIDs and opioids', null),
(2, 'Antibiotics', 'Antibacterial medicines for treating infections', null),
(3, 'Vitamins & Supplements', 'Nutritional supplements and vitamin preparations', null),
(4, 'Cardiovascular', 'Heart and blood pressure medications', null),
(5, 'Diabetes Care', 'Insulin, oral hypoglycemics and diabetes supplies', null),
(6, 'Dermatology', 'Skin care and dermatological preparations', null),
(7, 'Respiratory', 'Medicines for asthma, COPD and respiratory conditions', null),
(8, 'Gastrointestinal', 'Digestive system medicines and antacids', null),
(9, 'First Aid', 'Bandages, antiseptics and wound care products', null),
(10, 'Baby Care', 'Infant care products and pediatric medicines', null);

-- 5. EMPLOYER BANK DETAILS TABLE
INSERT INTO employer_bankdetails (employer_bank_details_id, bank_name, bank_branch_name, bank_account_number, employer_description, cashier_monthly_payment, payment_status, employeer_id) VALUES
(1, 'Bank of Ceylon', 'Colombo Main Branch', '1234567890', 'Owner primary salary account', 150000.00, true, 1),
(2, 'Sampath Bank', 'Maradana Branch', '0987654321', 'Cashier salary account', 45000.00, true, 2),
(3, 'Commercial Bank', 'Kandy Branch', '1122334455', 'Manager salary account', 85000.00, true, 3),
(4, 'HNB', 'Galle Branch', '5566778899', 'Cashier salary account', 42000.00, true, 4),
(5, 'NSB', 'Negombo Branch', '9988776655', 'Cashier salary account', 40000.00, true, 5),
(6, 'Peoples Bank', 'Colombo Fort', '1357924680', 'Branch manager account', 75000.00, true, 6),
(7, 'BOC', 'Kandy City', '2468013579', 'Assistant manager account', 65000.00, true, 7),
(8, 'Sampath Bank', 'Nugegoda', '3692581470', 'Cashier account', 38000.00, true, 8);

-- 6. EMPLOYER TABLE
-- Passwords are BCrypt encoded. Default passwords:
-- admin@lifepill.com: admin123
-- All others: password123
INSERT INTO employer (employer_id, employer_nic_name, employer_first_name, employer_last_name, profile_image, profile_image_url, employer_password, employer_email, employer_phone, employer_address, employer_sallary, employer_nic, is_active, pin, gender, employer_date_of_birth, role, brach_id, employer_bank_details_id) VALUES
(1, 'pramitha', 'Pramitha', 'Jayasooriya', null, null, '$2a$10$PODkaZ3NMuBQJnslHP9hou.j8Q4BxaXamd63.c5/743uQz1.H4Bqi', 'admin@lifepill.com', '0771234567', 'LifePill Head Office, 123 Main Street, Colombo 03', 150000.00, '200012345678', true, 1234, 'MALE', '1990-01-15', 'OWNER', 1, 1),
(2, 'jane', 'Jane', 'Doe', null, null, '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'cashier1@lifepill.com', '0774445555', 'Maradana Branch, Colombo 10', 45000.00, '199544556677', true, 4321, 'FEMALE', '1995-07-20', 'CASHIER', 2, 2),
(3, 'kumar', 'Kumar', 'Sangakkara', null, null, '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'manager.kandy@lifepill.com', '0772223333', '45 Temple Road, Kandy', 85000.00, '198833445566', true, 1111, 'MALE', '1988-03-10', 'MANAGER', 3, 3),
(4, 'malini', 'Malini', 'Fernando', null, null, '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'cashier.galle@lifepill.com', '0775556666', '78 Beach Road, Galle', 42000.00, '199722334455', true, 2222, 'FEMALE', '1997-11-25', 'CASHIER', 4, 4),
(5, 'nimal', 'Nimal', 'Perera', null, null, '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'cashier.negombo@lifepill.com', '0776667777', '90 Lewis Place, Negombo', 40000.00, '199611223344', true, 3333, 'MALE', '1996-05-18', 'CASHIER', 5, 5),
(6, 'samantha', 'Samantha', 'Silva', null, null, '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'manager.colombo@lifepill.com', '0777778888', '25 Galle Road, Colombo 04', 75000.00, '199055667788', true, 4444, 'MALE', '1990-09-05', 'MANAGER', 1, 6),
(7, 'chamari', 'Chamari', 'Wijesinghe', null, null, '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'assistant.kandy@lifepill.com', '0778889999', '67 Peradeniya Road, Kandy', 65000.00, '199244556677', true, 5555, 'FEMALE', '1992-02-14', 'MANAGER', 3, 7),
(8, 'ruwan', 'Ruwan', 'Jayawardena', null, null, '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'cashier2@lifepill.com', '0779990000', '34 High Level Road, Nugegoda', 38000.00, '199888990011', true, 6666, 'MALE', '1998-08-30', 'CASHIER', 1, 8);

-- 7. ITEM TABLE
INSERT INTO item (item_id, item_name, selling_price, item_barcode, supply_date, supplier_price, is_free_issued, is_discounted, item_manufacture, item_quantity, is_stock, measuring_unit_type, manufacture_date, expire_date, purchase_date, warranty_period, rack_number, discounted_price, discounted_percentage, warehouse_name, is_special_condition, item_image, item_description, date_created, last_updated_date, category_id, supplier_id, branch_id) VALUES
-- Analgesics (Category 1)
(1, 'Paracetamol 500mg', 50.00, 'P500-0001', '2025-04-27', 30.00, false, false, 'ABC Labs', 500, true, 'TABLETS', '2024-11-27', '2027-11-27', '2025-04-27', null, 'R1-A1', 0, 0, 'Main Warehouse', false, null, 'Paracetamol tablets for pain and fever relief', '2025-05-27', '2025-11-27', 1, 1, 1),
(2, 'Ibuprofen 400mg', 75.00, 'I400-0002', '2025-03-15', 45.00, false, false, 'ABC Labs', 300, true, 'TABLETS', '2024-09-15', '2026-09-15', '2025-03-15', null, 'R1-A2', 0, 0, 'Main Warehouse', false, null, 'Anti-inflammatory pain relief tablets', '2025-03-20', '2025-11-27', 1, 1, 1),
(3, 'Aspirin 300mg', 35.00, 'A300-0003', '2025-02-10', 20.00, false, false, 'ABC Labs', 400, true, 'TABLETS', '2024-08-10', '2026-08-10', '2025-02-10', null, 'R1-A3', 0, 0, 'Main Warehouse', false, null, 'Aspirin for pain relief and blood thinning', '2025-02-15', '2025-11-27', 1, 1, 1),
(4, 'Diclofenac 50mg', 85.00, 'D050-0004', '2025-05-01', 55.00, false, true, 'XYZ Labs', 200, true, 'TABLETS', '2024-11-01', '2026-11-01', '2025-05-01', null, 'R1-A4', 75.00, 12, 'Main Warehouse', false, null, 'NSAID for inflammation and pain', '2025-05-05', '2025-11-27', 1, 1, 1),

-- Antibiotics (Category 2)
(5, 'Amoxicillin 250mg', 120.00, 'A250-0005', '2025-03-27', 70.00, false, false, 'XYZ Labs', 250, true, 'CAPSULE', '2024-11-27', '2026-11-27', '2025-03-27', null, 'R2-B1', 0, 0, 'Main Warehouse', true, null, 'Broad spectrum antibiotic capsules', '2025-04-01', '2025-11-27', 2, 2, 1),
(6, 'Amoxicillin 500mg', 180.00, 'A500-0006', '2025-03-27', 110.00, false, false, 'XYZ Labs', 200, true, 'CAPSULE', '2024-11-27', '2026-11-27', '2025-03-27', null, 'R2-B2', 0, 0, 'Main Warehouse', true, null, 'Higher dose antibiotic for severe infections', '2025-04-01', '2025-11-27', 2, 2, 1),
(7, 'Azithromycin 500mg', 250.00, 'AZ50-0007', '2025-04-15', 160.00, false, false, 'XYZ Labs', 150, true, 'TABLETS', '2024-10-15', '2026-10-15', '2025-04-15', null, 'R2-B3', 0, 0, 'Main Warehouse', true, null, 'Macrolide antibiotic for respiratory infections', '2025-04-20', '2025-11-27', 2, 2, 1),
(8, 'Ciprofloxacin 500mg', 150.00, 'C500-0008', '2025-05-10', 90.00, false, false, 'ABC Labs', 180, true, 'TABLETS', '2024-11-10', '2026-11-10', '2025-05-10', null, 'R2-B4', 0, 0, 'Main Warehouse', true, null, 'Fluoroquinolone antibiotic', '2025-05-15', '2025-11-27', 2, 2, 2),

-- Vitamins & Supplements (Category 3)
(9, 'Vitamin C 500mg', 95.00, 'VC50-0009', '2025-01-20', 55.00, false, false, 'Wellness Labs', 600, true, 'TABLETS', '2024-07-20', '2027-07-20', '2025-01-20', null, 'R3-C1', 0, 0, 'Main Warehouse', false, null, 'Immune boosting vitamin C supplement', '2025-01-25', '2025-11-27', 3, 3, 1),
(10, 'Vitamin D3 1000IU', 120.00, 'VD10-0010', '2025-02-15', 75.00, false, false, 'Wellness Labs', 400, true, 'CAPSULE', '2024-08-15', '2027-08-15', '2025-02-15', null, 'R3-C2', 0, 0, 'Main Warehouse', false, null, 'Vitamin D for bone health', '2025-02-20', '2025-11-27', 3, 3, 1),
(11, 'Multivitamin Complex', 350.00, 'MV00-0011', '2025-03-01', 220.00, false, true, 'Wellness Labs', 300, true, 'TABLETS', '2024-09-01', '2026-09-01', '2025-03-01', null, 'R3-C3', 315.00, 10, 'Main Warehouse', false, null, 'Complete daily multivitamin supplement', '2025-03-05', '2025-11-27', 3, 3, 2),
(12, 'Omega-3 Fish Oil', 450.00, 'OM30-0012', '2025-04-10', 300.00, false, false, 'Wellness Labs', 250, true, 'CAPSULE', '2024-10-10', '2026-10-10', '2025-04-10', null, 'R3-C4', 0, 0, 'Main Warehouse', false, null, 'Heart healthy fish oil capsules', '2025-04-15', '2025-11-27', 3, 3, 2),
(13, 'Calcium + Vitamin D', 280.00, 'CAD0-0013', '2025-05-05', 180.00, false, false, 'Wellness Labs', 350, true, 'TABLETS', '2024-11-05', '2027-11-05', '2025-05-05', null, 'R3-C5', 0, 0, 'Main Warehouse', false, null, 'Bone health supplement', '2025-05-10', '2025-11-27', 3, 3, 3),

-- Cardiovascular (Category 4)
(14, 'Amlodipine 5mg', 95.00, 'AM05-0014', '2025-02-20', 55.00, false, false, 'CareFirst Pharma', 400, true, 'TABLETS', '2024-08-20', '2026-08-20', '2025-02-20', null, 'R4-D1', 0, 0, 'Main Warehouse', true, null, 'Calcium channel blocker for hypertension', '2025-02-25', '2025-11-27', 4, 4, 1),
(15, 'Atenolol 50mg', 85.00, 'AT50-0015', '2025-03-10', 50.00, false, false, 'CareFirst Pharma', 350, true, 'TABLETS', '2024-09-10', '2026-09-10', '2025-03-10', null, 'R4-D2', 0, 0, 'Main Warehouse', true, null, 'Beta blocker for heart conditions', '2025-03-15', '2025-11-27', 4, 4, 1),
(16, 'Losartan 50mg', 110.00, 'LO50-0016', '2025-04-01', 70.00, false, false, 'CareFirst Pharma', 280, true, 'TABLETS', '2024-10-01', '2026-10-01', '2025-04-01', null, 'R4-D3', 0, 0, 'Main Warehouse', true, null, 'ARB for blood pressure control', '2025-04-05', '2025-11-27', 4, 4, 3),
(17, 'Atorvastatin 20mg', 150.00, 'AT20-0017', '2025-05-15', 95.00, false, false, 'CareFirst Pharma', 320, true, 'TABLETS', '2024-11-15', '2026-11-15', '2025-05-15', null, 'R4-D4', 0, 0, 'Main Warehouse', true, null, 'Statin for cholesterol management', '2025-05-20', '2025-11-27', 4, 4, 3),

-- Diabetes Care (Category 5)
(18, 'Metformin 500mg', 65.00, 'ME50-0018', '2025-01-25', 35.00, false, false, 'DiabeCare Labs', 500, true, 'TABLETS', '2024-07-25', '2026-07-25', '2025-01-25', null, 'R5-E1', 0, 0, 'Main Warehouse', true, null, 'First-line diabetes medication', '2025-01-30', '2025-11-27', 5, 5, 1),
(19, 'Metformin 850mg', 85.00, 'ME85-0019', '2025-02-05', 50.00, false, false, 'DiabeCare Labs', 400, true, 'TABLETS', '2024-08-05', '2026-08-05', '2025-02-05', null, 'R5-E2', 0, 0, 'Main Warehouse', true, null, 'Higher dose metformin', '2025-02-10', '2025-11-27', 5, 5, 1),
(20, 'Glimepiride 2mg', 95.00, 'GL02-0020', '2025-03-20', 60.00, false, false, 'DiabeCare Labs', 300, true, 'TABLETS', '2024-09-20', '2026-09-20', '2025-03-20', null, 'R5-E3', 0, 0, 'Main Warehouse', true, null, 'Sulfonylurea for type 2 diabetes', '2025-03-25', '2025-11-27', 5, 5, 4),
(21, 'Blood Glucose Test Strips', 1200.00, 'BG00-0021', '2025-04-20', 850.00, false, true, 'DiabeCare Labs', 150, true, 'NUMBER', '2024-10-20', '2026-04-20', '2025-04-20', null, 'R5-E4', 1080.00, 10, 'Main Warehouse', false, null, 'Blood glucose monitoring strips (50 pack)', '2025-04-25', '2025-11-27', 5, 5, 4),

-- Dermatology (Category 6)
(22, 'Hydrocortisone Cream 1%', 180.00, 'HC01-0022', '2025-02-28', 110.00, false, false, 'SkinHealth Labs', 200, true, 'GRAM', '2024-08-28', '2026-08-28', '2025-02-28', null, 'R6-F1', 0, 0, 'Main Warehouse', false, null, 'Topical corticosteroid cream', '2025-03-05', '2025-11-27', 6, 6, 1),
(23, 'Clotrimazole Cream', 150.00, 'CL00-0023', '2025-03-15', 90.00, false, false, 'SkinHealth Labs', 180, true, 'GRAM', '2024-09-15', '2026-09-15', '2025-03-15', null, 'R6-F2', 0, 0, 'Main Warehouse', false, null, 'Antifungal cream for skin infections', '2025-03-20', '2025-11-27', 6, 6, 2),
(24, 'Sunscreen SPF 50', 650.00, 'SS50-0024', '2025-04-25', 420.00, false, true, 'SkinHealth Labs', 120, true, 'MILLI_LITER', '2024-10-25', '2026-10-25', '2025-04-25', null, 'R6-F3', 585.00, 10, 'Main Warehouse', false, null, 'High protection sunscreen lotion', '2025-04-30', '2025-11-27', 6, 6, 5),

-- Respiratory (Category 7)
(25, 'Salbutamol Inhaler', 450.00, 'SA00-0025', '2025-01-15', 280.00, false, false, 'ABC Labs', 100, true, 'SPRAY', '2024-07-15', '2026-07-15', '2025-01-15', null, 'R7-G1', 0, 0, 'Main Warehouse', true, null, 'Bronchodilator inhaler for asthma', '2025-01-20', '2025-11-27', 7, 1, 1),
(26, 'Cetirizine 10mg', 55.00, 'CE10-0026', '2025-02-25', 30.00, false, false, 'XYZ Labs', 450, true, 'TABLETS', '2024-08-25', '2026-08-25', '2025-02-25', null, 'R7-G2', 0, 0, 'Main Warehouse', false, null, 'Antihistamine for allergies', '2025-03-01', '2025-11-27', 7, 2, 2),
(27, 'Montelukast 10mg', 180.00, 'MO10-0027', '2025-04-05', 110.00, false, false, 'ABC Labs', 200, true, 'TABLETS', '2024-10-05', '2026-10-05', '2025-04-05', null, 'R7-G3', 0, 0, 'Main Warehouse', true, null, 'Leukotriene receptor antagonist', '2025-04-10', '2025-11-27', 7, 1, 3),

-- Gastrointestinal (Category 8)
(28, 'Omeprazole 20mg', 75.00, 'OM20-0028', '2025-03-05', 45.00, false, false, 'ABC Labs', 400, true, 'CAPSULE', '2024-09-05', '2026-09-05', '2025-03-05', null, 'R8-H1', 0, 0, 'Main Warehouse', false, null, 'Proton pump inhibitor for acid reflux', '2025-03-10', '2025-11-27', 8, 1, 1),
(29, 'Ranitidine 150mg', 60.00, 'RA15-0029', '2025-04-15', 35.00, false, false, 'XYZ Labs', 350, true, 'TABLETS', '2024-10-15', '2026-10-15', '2025-04-15', null, 'R8-H2', 0, 0, 'Main Warehouse', false, null, 'H2 blocker for ulcers', '2025-04-20', '2025-11-27', 8, 2, 2),
(30, 'Antacid Tablets', 45.00, 'AN00-0030', '2025-05-20', 25.00, false, false, 'Wellness Labs', 600, true, 'TABLETS', '2024-11-20', '2027-11-20', '2025-05-20', null, 'R8-H3', 0, 0, 'Main Warehouse', false, null, 'Quick relief antacid chewable tablets', '2025-05-25', '2025-11-27', 8, 3, 4),

-- First Aid (Category 9)
(31, 'Bandage Roll 5cm', 85.00, 'BR05-0031', '2025-01-10', 50.00, false, false, 'MediSupply', 300, true, 'NUMBER', '2024-07-10', '2027-07-10', '2025-01-10', null, 'R9-I1', 0, 0, 'Main Warehouse', false, null, 'Elastic bandage roll for wound dressing', '2025-01-15', '2025-11-27', 9, 3, 1),
(32, 'Antiseptic Solution 100ml', 120.00, 'AS10-0032', '2025-02-18', 75.00, false, false, 'MediSupply', 250, true, 'MILLI_LITER', '2024-08-18', '2026-08-18', '2025-02-18', null, 'R9-I2', 0, 0, 'Main Warehouse', false, null, 'Povidone-iodine antiseptic solution', '2025-02-23', '2025-11-27', 9, 3, 2),
(33, 'Cotton Wool 100g', 95.00, 'CW10-0033', '2025-03-25', 55.00, false, false, 'MediSupply', 400, true, 'GRAM', '2024-09-25', '2027-09-25', '2025-03-25', null, 'R9-I3', 0, 0, 'Main Warehouse', false, null, 'Medical grade cotton wool', '2025-03-30', '2025-11-27', 9, 3, 3),
(34, 'First Aid Kit Complete', 1500.00, 'FA00-0034', '2025-05-01', 950.00, false, true, 'MediSupply', 50, true, 'NUMBER', '2024-11-01', '2027-11-01', '2025-05-01', null, 'R9-I4', 1350.00, 10, 'Main Warehouse', false, null, 'Complete first aid kit for home and office', '2025-05-05', '2025-11-27', 9, 3, 5),

-- Baby Care (Category 10)
(35, 'Baby Paracetamol Syrup', 180.00, 'BP00-0035', '2025-02-12', 110.00, false, false, 'ABC Labs', 200, true, 'MILLI_LITER', '2024-08-12', '2026-08-12', '2025-02-12', null, 'R10-J1', 0, 0, 'Main Warehouse', true, null, 'Pediatric fever and pain relief syrup', '2025-02-17', '2025-11-27', 10, 1, 1),
(36, 'Baby Vitamin Drops', 350.00, 'BV00-0036', '2025-03-08', 220.00, false, false, 'Wellness Labs', 150, true, 'DROPS', '2024-09-08', '2026-09-08', '2025-03-08', null, 'R10-J2', 0, 0, 'Main Warehouse', false, null, 'Multivitamin drops for infants', '2025-03-13', '2025-11-27', 10, 3, 2),
(37, 'Diaper Rash Cream', 280.00, 'DR00-0037', '2025-04-22', 175.00, false, false, 'SkinHealth Labs', 180, true, 'GRAM', '2024-10-22', '2027-10-22', '2025-04-22', null, 'R10-J3', 0, 0, 'Main Warehouse', false, null, 'Zinc oxide cream for diaper rash', '2025-04-27', '2025-11-27', 10, 6, 4),
(38, 'Gripe Water 150ml', 220.00, 'GW15-0038', '2025-05-18', 140.00, false, false, 'Wellness Labs', 220, true, 'MILLI_LITER', '2024-11-18', '2026-11-18', '2025-05-18', null, 'R10-J4', 0, 0, 'Main Warehouse', false, null, 'Natural remedy for infant colic', '2025-05-23', '2025-11-27', 10, 3, 5);

-- 8. BRANCH ITEM TABLE (Many-to-Many relationship)
INSERT INTO branch_item (branch_id, item_id) VALUES
-- Branch 1 (Head Office) - All categories represented
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 9), (1, 10),
(1, 14), (1, 15), (1, 18), (1, 19), (1, 22), (1, 25), (1, 28), (1, 31), (1, 35),
-- Branch 2 (Maradana)
(2, 1), (2, 2), (2, 5), (2, 8), (2, 11), (2, 12), (2, 23), (2, 26), (2, 29), (2, 32), (2, 36),
-- Branch 3 (Kandy)
(3, 1), (3, 3), (3, 6), (3, 7), (3, 13), (3, 16), (3, 17), (3, 20), (3, 27), (3, 33),
-- Branch 4 (Galle)
(4, 1), (4, 4), (4, 5), (4, 20), (4, 21), (4, 30), (4, 37),
-- Branch 5 (Negombo)
(5, 1), (5, 9), (5, 24), (5, 34), (5, 38);

-- 9. ORDERS TABLE
INSERT INTO orders (order_id, employer_id, branch_id, order_date, total) VALUES
(1, 1, 1, '2025-11-20 09:30:00', 350.00),
(2, 2, 2, '2025-11-20 10:15:00', 580.00),
(3, 4, 4, '2025-11-20 11:00:00', 1200.00),
(4, 5, 5, '2025-11-21 09:00:00', 2150.00),
(5, 2, 2, '2025-11-21 14:30:00', 450.00),
(6, 1, 1, '2025-11-22 10:00:00', 890.00),
(7, 8, 1, '2025-11-22 11:30:00', 275.00),
(8, 4, 4, '2025-11-23 09:45:00', 1650.00),
(9, 2, 2, '2025-11-23 15:00:00', 380.00),
(10, 5, 5, '2025-11-24 10:30:00', 750.00),
(11, 1, 1, '2025-11-25 09:00:00', 1100.00),
(12, 8, 1, '2025-11-25 14:00:00', 420.00),
(13, 2, 2, '2025-11-26 10:00:00', 560.00),
(14, 4, 4, '2025-11-26 11:30:00', 980.00),
(15, 5, 5, '2025-11-27 09:15:00', 1450.00);

-- 10. ORDER DETAILS TABLE
INSERT INTO order_details (order_details_id, name, amount, order_id, item_id) VALUES
-- Order 1
(1, 'Paracetamol 500mg', 4, 1, 1),
(2, 'Ibuprofen 400mg', 2, 1, 2),
-- Order 2
(3, 'Amoxicillin 250mg', 2, 2, 5),
(4, 'Vitamin C 500mg', 2, 2, 9),
(5, 'Cetirizine 10mg', 2, 2, 26),
-- Order 3
(6, 'Blood Glucose Test Strips', 1, 3, 21),
-- Order 4
(7, 'First Aid Kit Complete', 1, 4, 34),
(8, 'Sunscreen SPF 50', 1, 4, 24),
-- Order 5
(9, 'Omeprazole 20mg', 3, 5, 28),
(10, 'Multivitamin Complex', 1, 5, 11),
-- Order 6
(11, 'Amlodipine 5mg', 4, 6, 14),
(12, 'Atenolol 50mg', 2, 6, 15),
(13, 'Salbutamol Inhaler', 1, 6, 25),
-- Order 7
(14, 'Paracetamol 500mg', 3, 7, 1),
(15, 'Aspirin 300mg', 2, 7, 3),
-- Order 8
(16, 'Metformin 500mg', 4, 8, 18),
(17, 'Glimepiride 2mg', 4, 8, 20),
(18, 'Blood Glucose Test Strips', 1, 8, 21),
-- Order 9
(19, 'Ciprofloxacin 500mg', 2, 9, 8),
-- Order 10
(20, 'Gripe Water 150ml', 2, 10, 38),
(21, 'Baby Vitamin Drops', 1, 10, 36),
-- Order 11
(22, 'Atorvastatin 20mg', 3, 11, 17),
(23, 'Losartan 50mg', 3, 11, 16),
(24, 'Omega-3 Fish Oil', 1, 11, 12),
-- Order 12
(25, 'Hydrocortisone Cream 1%', 1, 12, 22),
(26, 'Bandage Roll 5cm', 2, 12, 31),
-- Order 13
(27, 'Azithromycin 500mg', 2, 13, 7),
-- Order 14
(28, 'Diaper Rash Cream', 2, 14, 37),
(29, 'Baby Paracetamol Syrup', 2, 14, 35),
-- Order 15
(30, 'First Aid Kit Complete', 1, 15, 34);

-- 11. PAYMENT DETAILS TABLE
INSERT INTO payment_details (payment_id, payment_method, payment_amount, payment_date, payment_notes, payment_discount, paid_amount, order_id) VALUES
(1, 'CASH', 350.00, '2025-11-20 09:35:00', 'Paid in full', 0, 350.00, 1),
(2, 'CARD', 580.00, '2025-11-20 10:20:00', 'Visa card payment', 0, 580.00, 2),
(3, 'CASH', 1200.00, '2025-11-20 11:05:00', 'Paid with exact amount', 0, 1200.00, 3),
(4, 'CARD', 2150.00, '2025-11-21 09:10:00', 'Mastercard payment', 0, 2150.00, 4),
(5, 'CASH', 450.00, '2025-11-21 14:35:00', 'Cash payment', 0, 450.00, 5),
(6, 'CARD', 890.00, '2025-11-22 10:05:00', 'Debit card', 0, 890.00, 6),
(7, 'CASH', 275.00, '2025-11-22 11:35:00', 'Exact change given', 0, 275.00, 7),
(8, 'CARD', 1650.00, '2025-11-23 09:50:00', 'Credit card payment', 0, 1650.00, 8),
(9, 'CASH', 380.00, '2025-11-23 15:05:00', null, 0, 380.00, 9),
(10, 'CARD', 750.00, '2025-11-24 10:35:00', 'Visa card', 0, 750.00, 10),
(11, 'CASH', 1100.00, '2025-11-25 09:05:00', 'Paid in full', 0, 1100.00, 11),
(12, 'CASH', 420.00, '2025-11-25 14:05:00', 'Cash payment', 0, 420.00, 12),
(13, 'CARD', 560.00, '2025-11-26 10:05:00', 'Contactless payment', 0, 560.00, 13),
(14, 'CASH', 980.00, '2025-11-26 11:35:00', null, 0, 980.00, 14),
(15, 'CARD', 1450.00, '2025-11-27 09:20:00', 'Split payment not required', 0, 1450.00, 15);

-- RE-ENABLE FOREIGN KEY CHECKS
SET session_replication_role = 'origin';

COMMIT;

-- RESET SEQUENCE VALUES (Hibernate uses hibernate_sequence)
SELECT setval('hibernate_sequence', GREATEST(
    (SELECT COALESCE(MAX(branch_id), 0) FROM branch),
    (SELECT COALESCE(MAX(category_id), 0) FROM item_category),
    (SELECT COALESCE(MAX(employer_bank_details_id), 0) FROM employer_bankdetails),
    (SELECT COALESCE(MAX(employer_id), 0) FROM employer),
    (SELECT COALESCE(MAX(supplier_id), 0) FROM supplier),
    (SELECT COALESCE(MAX(company_id), 0) FROM supplier_company),
    (SELECT COALESCE(MAX(item_id), 0) FROM item),
    (SELECT COALESCE(MAX(order_id), 0) FROM orders),
    (SELECT COALESCE(MAX(order_details_id), 0) FROM order_details),
    (SELECT COALESCE(MAX(payment_id), 0) FROM payment_details)
) + 1, false);

SELECT setval('employer_bankdetails_employer_bank_details_id_seq', (SELECT COALESCE(MAX(employer_bank_details_id), 0) + 1 FROM employer_bankdetails), false);
SELECT setval('item_category_category_id_seq', (SELECT COALESCE(MAX(category_id), 0) + 1 FROM item_category), false);

-- SEED SCRIPT COMPLETE
-- Summary:
-- - 4 Supplier Companies
-- - 6 Suppliers
-- - 5 Branches
-- - 10 Item Categories
-- - 8 Employers (1 Owner, 2 Managers, 5 Cashiers)
-- - 8 Employer Bank Details
-- - 38 Items across all categories
-- - 15 Orders with 30 Order Details
-- - 15 Payments
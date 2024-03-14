INSERT INTO item (
    is_discounted,
    discounted_percentage,
    discounted_price,
    expire_date,
    is_free_issued,
    item_barcode,
    item_category,
    item_description,
    item_id,
    item_image,
    item_manufacture,
    item_name,
    item_quantity,
    measuring_unit_type,
    purchase_date,
    rack_number,
    selling_price,
    is_special_condition,
    is_stock,
    supplier_price,
    supply_date,
    warehouse_name,
    warranty_period
) VALUES
-- Medicine 1
(true, 10, 90, '2024-03-20', false, '123456789', 'Medicine', 'Pain Relief Tablets', 1, 'pain_relief_tablets.jpg', 'ABC Pharmaceuticals', 'PainEase', 500, 'TABLETS', '2024-03-10', 'Medicine Rack A', 100, false, true, 80, '2024-03-10', 'Pharmacy Warehouse A', '2 Years'),

-- Medicine 2
(false, 0, 0, '2024-04-15', true, '987654321', 'Medicine', 'Cough Syrup', 2, 'cough_syrup.jpg', 'XYZ Pharmaceuticals', 'CoughGone', 200, 'MILLI_LITER', '2024-02-20', 'Medicine Rack B', 15, true, true, 10, '2024-02-15', 'Pharmacy Warehouse B', 'N/A'),

-- Medicine 3
(true, 20, 80, '2024-05-25', false, '135792468', 'Medicine', 'Antibiotic Capsules', 3, 'antibiotic_capsules.jpg', 'DEF Pharmaceuticals', 'AntiBioticX', 100, 'CAPSULE', '2024-03-05', 'Medicine Rack C', 150, true, true, 120, '2024-03-01', 'Pharmacy Warehouse C', '1 Year'),

-- Medicine 4
(false, 0, 0, '2024-06-30', true, '246813579', 'Medicine', 'Pain Relief Spray', 4, 'pain_relief_spray.jpg', 'GHI Pharmaceuticals', 'SprayAway', 50, 'SPRAY', '2024-03-10', 'Medicine Rack D', 80, false, true, 70, '2024-03-05', 'Pharmacy Warehouse D', 'N/A'),

-- Medicine 5
(true, 15, 85, '2024-07-10', false, '369258147', 'Medicine', 'Vitamin Tablets', 5, 'vitamin_tablets.jpg', 'JKL Pharmaceuticals', 'VitaBoost', 300, 'TABLETS', '2024-03-15', 'Medicine Rack E', 200, false, true, 180, '2024-03-10', 'Pharmacy Warehouse E', '1 Year'),

-- Medicine 6
(false, 0, 0, '2024-08-05', true, '159357486', 'Medicine', 'Eye Drops', 6, 'eye_drops.jpg', 'MNO Pharmaceuticals', 'ClearSight', 100, 'DROPS', '2024-01-25', 'Medicine Rack F', 30, true, true, 25, '2024-01-20', 'Pharmacy Warehouse F', 'N/A'),

-- Medicine 7
(true, 25, 75, '2024-09-15', false, '789456123', 'Medicine', 'Anti-inflammatory Tablets', 7, 'anti_inflammatory_tablets.jpg', 'PQR Pharmaceuticals', 'InflamEase', 150, 'TABLETS', '2024-02-28', 'Medicine Rack G', 120, false, true, 100, '2024-02-25', 'Pharmacy Warehouse G', '1 Year'),

-- Medicine 8
(false, 0, 0, '2024-10-20', true, '258147369', 'Medicine', 'Throat Spray', 8, 'throat_spray.jpg', 'STU Pharmaceuticals', 'ThroatSoothe', 80, 'SPRAY', '2024-01-15', 'Medicine Rack H', 60, true, true, 50, '2024-01-10', 'Pharmacy Warehouse H', 'N/A'),

-- Medicine 9
(true, 18, 82, '2024-11-25', false, '147258369', 'Medicine', 'Allergy Capsules', 9, 'allergy_capsules.jpg', 'VWX Pharmaceuticals', 'AllergiClear', 200, 'CAPSULE', '2024-02-05', 'Medicine Rack I', 180, true, true, 150, '2024-02-01', 'Pharmacy Warehouse I', '1 Year'),

-- Medicine 10
(false, 0, 0, '2024-12-30', true, '369147258', 'Medicine', 'Nasal Spray', 10, 'nasal_spray.jpg', 'YZA Pharmaceuticals', 'NoseClear', 100, 'SPRAY', '2024-01-20', 'Medicine Rack J', 80, true, true, 70, '2024-01-15', 'Pharmacy Warehouse J', 'N/A'),

-- Medicine 11
(true, 12, 88, '2025-01-05', false, '987654321', 'Medicine', 'Digestive Tablets', 11, 'digestive_tablets.jpg', 'ABC Pharmaceuticals', 'DigestiCare', 300, 'TABLETS', '2024-03-25', 'Medicine Rack K', 250, true, true, 220, '2024-03-20', 'Pharmacy Warehouse K', '2 Years'),

-- Medicine 12
(false, 0, 0, '2025-02-10', true, '123456789', 'Medicine', 'Oral Suspension', 12, 'oral_suspension.jpg', 'XYZ Pharmaceuticals', 'OralRelief', 150, 'MILLI_LITER', '2024-02-10', 'Medicine Rack L', 120, true, true, 100, '2024-02-05', 'Pharmacy Warehouse L', 'N/A'),

-- Medicine 13
(true, 20, 80, '2025-03-15', false, '369258147', 'Medicine', 'Fever Reducer Tablets', 13, 'fever_reducer_tablets.jpg', 'DEF Pharmaceuticals', 'FeverDown', 200, 'TABLETS', '2024-03-15', 'Medicine Rack M', 180, false, true, 160, '2024-03-10', 'Pharmacy Warehouse M', '1 Year'),

-- Medicine 14
(false, 0, 0, '2025-04-20', true, '159357486', 'Medicine', 'Antacid Tablets', 14, 'antacid_tablets.jpg', 'GHI Pharmaceuticals', 'AcidEase', 100, 'TABLETS', '2024-01-20', 'Medicine Rack N', 80, true, true, 70, '2024-01-15', 'Pharmacy Warehouse N', 'N/A'),

-- Medicine 15
(true, 15, 85, '2025-05-25', false, '789456123', 'Medicine', 'Sleep Aid Capsules', 15, 'sleep_aid_capsules.jpg', 'JKL Pharmaceuticals', 'DreamRest', 120, 'CAPSULE', '2024-02-25', 'Medicine Rack O', 100, false, true, 90, '2024-02-20', 'Pharmacy Warehouse O', '1 Year'),

-- Medicine 16
(false, 0, 0, '2025-06-30', true, '258147369', 'Medicine', 'Nasal Drops', 16, 'nasal_drops.jpg', 'MNO Pharmaceuticals', 'NoseRelief', 50, 'DROPS', '2024-01-15', 'Medicine Rack P', 40, true, true, 30, '2024-01-10', 'Pharmacy Warehouse P', 'N/A'),

-- Medicine 17
(true, 18, 82, '2025-07-05', false, '147258369', 'Medicine', 'Antihistamine Tablets', 17, 'antihistamine_tablets.jpg', 'PQR Pharmaceuticals', 'HistAll', 200, 'TABLETS', '2024-02-01', 'Medicine Rack Q', 180, true, true, 160, '2024-01-25', 'Pharmacy Warehouse Q', '1 Year'),

-- Medicine 18
(false, 0, 0, '2025-08-10', true, '369147258', 'Medicine', 'Pain Relief Spray', 18, 'pain_relief_spray.jpg', 'STU Pharmaceuticals', 'PainGone', 100, 'SPRAY', '2024-01-20', 'Medicine Rack R', 80, true, true, 70, '2024-01-15', 'Pharmacy Warehouse R', 'N/A'),

-- Medicine 19
(true, 12, 88, '2025-09-15', false, '987654321', 'Medicine', 'Antibiotic Ointment', 19, 'antibiotic_ointment.jpg', 'VWX Pharmaceuticals', 'AntiBac', 150, 'GRAM', '2024-03-25', 'Medicine Rack S', 120, true, true, 100, '2024-03-20', 'Pharmacy Warehouse S', '2 Years'),

-- Medicine 20
(false, 0, 0, '2025-10-20', true, '123456789', 'Medicine', 'Pain Relief Gel', 20, 'pain_relief_gel.jpg', 'YZA Pharmaceuticals', 'PainReliever', 80, 'GRAM', '2024-02-10', 'Medicine Rack T', 60, true, true, 50, '2024-02-05', 'Pharmacy Warehouse T', 'N/A');

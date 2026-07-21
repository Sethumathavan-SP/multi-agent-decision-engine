INSERT INTO permissions (name) VALUES
('MANAGE_USERS'),
('VIEW_DASHBOARD'),
('RUN_DECISION'),
('APPROVE_DECISION'),
('RUN_SIMULATION');

INSERT INTO roles (name) VALUES
('ADMIN'),
('USER');

-- Assign all permissions to ADMIN role
INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id FROM roles r, permissions p WHERE r.name = 'ADMIN';

-- Assign specific permissions to USER role
INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id FROM roles r, permissions p WHERE r.name = 'USER' AND p.name IN ('VIEW_DASHBOARD', 'RUN_DECISION');
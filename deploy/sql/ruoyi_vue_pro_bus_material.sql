BEGIN;
INSERT INTO bus_material (id, code, name, spec, note, creator, create_time, updater, update_time, deleted, status) VALUES (1, 'HA0002', '线圈', '卷', '不知道', '1', '2022-02-03 17:22:27', '1', '2022-02-03 17:22:27', false, 0);
INSERT INTO bus_material (id, code, name, spec, note, creator, create_time, updater, update_time, deleted, status) VALUES (2, 'HA104', '炸弹', '个', null, '1', '2022-02-04 20:11:47', '1', '2022-02-04 20:11:47', false, 0);
INSERT INTO bus_material (id, code, name, spec, note, creator, create_time, updater, update_time, deleted, status) VALUES (3, 'HB001', 'hello', null, null, '1', '2022-02-04 22:54:20', '1', '2022-02-04 22:54:20', false, 0);
INSERT INTO bus_material (id, code, name, spec, note, creator, create_time, updater, update_time, deleted, status) VALUES (4, 'HB004', 'world', null, null, '1', '2022-02-04 22:54:28', '1', '2022-02-04 22:54:28', false, 0);
COMMIT;
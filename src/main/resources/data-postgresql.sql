

INSERT into country_group (country_id, country) values (1, 'China') ON CONFLICT DO NOTHING;
INSERT into country_group (country_id, country) values (2, 'Kenya') ON CONFLICT DO NOTHING;
INSERT into country_group (country_id, country) values (3, 'Norway') ON CONFLICT DO NOTHING;

INSERT into organization (org_id, org_name) values (1, 'MSF') ON CONFLICT DO NOTHING;
INSERT into organization (org_id, org_name) values (2, 'UNDP') ON CONFLICT DO NOTHING;
INSERT into organization (org_id, org_name) values (3, 'MERCER') ON CONFLICT DO NOTHING;
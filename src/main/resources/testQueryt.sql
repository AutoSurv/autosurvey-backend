select * from country_info;
select * from org_info;
select * from contact_info;
select * from function_info;
select * from function_salary_info;

select *
from org_info, contact_info, function_info, function_salary_info;

select *
from org_info, country_info;

select *
from org_info, contact_info;

select *
from function_info, function_salary_info;

inner join contact_info as ci on ci.contact_info_id_org_id = oi.org_id;




INSERT into country_info (country_info_id, country_name) 
values ('1', 'testCounrtry') ;
INSERT into country_info (country_info_id, country_name) 
values ('2', 'testCounrtry2') ;

INSERT into org_info (org_id, country_info_id, currency_in_use, org_full_name, org_name, thirteenth_salary, working_hours) 
values ('1', '1', 'test', 'Medicine sans frontiers', 'msf', 13, 8);
INSERT into org_info (org_id, country_info_id, currency_in_use, org_full_name, org_name, thirteenth_salary, working_hours) 
values ('2', '2', 'test2', 'Medicine sans frontiers2', 'msf2', 13, 8);

INSERT into contact_info (contact_info_id, contact_email, contact_job_title, contact_person, contact_phone) 
values (1, 'contact@msf.org', 'contacter', 'mr contact', '+123456789') ;

INSERT into function_info (function_info_id, function_name) 
values (1, 'testFUnctionJob1') ;
INSERT into function_info (function_info_id, function_name) 
values (2, 'testFUnctionJob2') ;

INSERT into function_salary_info (function_salary_info_id, function_custom_name, basic_salary, tgc, monthly_allowance, org_id, function_info_id) 
values (1, 'testJob1', 1000, 2000, 500,  1, 1) ;
INSERT into function_salary_info (function_salary_info_id, function_custom_name, basic_salary, tgc, monthly_allowance, org_id, function_info_id) 
values (2, 'testJob2', 1002, 202, 502,  1, 2) ;
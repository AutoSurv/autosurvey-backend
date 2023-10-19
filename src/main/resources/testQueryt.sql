select * from country_info;
select * from org_info;
select * from contact_info;
select * from function_info;
select * from function_salary_info;

/*Testing join between country, date, and org*/
select country_name, date, org_name, working_hours, currency_ref, currency_in_use
from org_info, country_info where org_info.country_info_id = country_info.country_info_id;

/*Testing join between country+date, and currency */
select country_name, date, currency_ref, currency, exchange_rate
from country_info join currency_info on country_info.country_info_id = currency_info.currency_info_id ;

/*Testing join between country+date, currency, and org */
select country_name, date, org_name, working_hours, currency_in_use, currency_ref, currency, exchange_rate
from country_info
join currency_info on country_info.country_info_id = currency_info.currency_info_id
join org_info on org_info.country_info_id = country_info.country_info_id;

/* */

SELECT C.country_name, C.year, O.org_name, O.org_full_name, F.msf_level, F.irffg_level, F.function_info_id, F.msf_function,
            FS.org_function_id, FS.org_function, C.currency_ref, O.currency_in_use, CUR.currency, CUR.exchange_rate, O.working_hours, O.thirteenth_salary,
            FS.basic_salary,
            FS.allowance_per_function, A.cost_of_living_allowance, A.transportation_allowance, A.housing_allowance, A.other_allowance,
            A.total_allowance, AP.cost_of_living_allowance_percent, AP.transportation_allowance_percent, AP.housing_allowance_percent,
            AP.other_allowance_percent, AP.total_allowance_percent, FS.tgc
            FROM country_info C
            JOIN org_info O ON C.country_info_id = O.country_info_id
			LEFT JOIN currency_info CUR ON (C.country_info_id = CUR.currency_info_id AND O.currency_info_id = CUR.currency_info_id)
            LEFT JOIN function_salary_info FS ON FS.org_id = O.org_id
			LEFT JOIN function_info F ON F.function_info_id = FS.function_info_id
			LEFT JOIN allowance_info A ON O.org_id = A.allowance_info_id
            LEFT JOIN allowance_percent_info AP ON O.org_id = AP.allowance_percent_info_id
            ORDER BY O.org_id, F.msf_level ;

select *
from org_info, country_info;

select *
from org_info, contact_info;

select *
from function_info, function_salary_info;

inner join contact_info as ci on ci.contact_info_id_org_id = oi.org_id;


/* 1.Insert data for country*/

INSERT into country_info (country_info_id, country_name, currency_ref, date)
values (1, 'South Africa', 'ZAR', '04-10-2023');
INSERT into country_info (country_info_id, country_name, currency_ref, date)
values (2, 'testCounrtry', 'EUR', '25-11-2003') ;

/* 2.Insert data for currency*/
INSERT into currency_info
values (1, 'USD', 15.126, 1) ;
INSERT into currency_info
values (2, 'YEN', 1.5, 2) ;

/* 3.Insert data for org*/
INSERT into org_info (org_id, org_full_name, org_name, country_info_id, currency_info_id, currency_in_use, thirteenth_salary, working_hours)
values (1, 'Medicine sans frontiers', 'msf', 1, null,'ZAR', 13, 40);
INSERT into org_info (org_id, org_full_name, org_name, country_info_id, currency_info_id, currency_in_use, thirteenth_salary, working_hours)
values (2, 'International SOS', 'International SOS', 1, 1, 'USD', 12, 38);
INSERT into org_info (org_id, org_full_name, org_name, country_info_id, currency_info_id, currency_in_use, thirteenth_salary, working_hours)
values (3, 'Medicine sans frontiers', 'msf', 2, 2, 'YEN', 13, 40);
INSERT into org_info (org_id, org_full_name, org_name, country_info_id, currency_info_id, currency_in_use, thirteenth_salary, working_hours)
values (4, 'Hello SOS', 'Hello SOS', 2, null,'EUR', 11, 39);

/* 4.Insert data for contact*/
INSERT into contact_info (contact_info_id, contact_email, contact_job_title, contact_person, contact_phone)
values (1, 'contact@msf.org', 'contacter', 'mr contact', '+123456789') ;
INSERT into contact_info (contact_info_id, contact_email, contact_job_title, contact_person, contact_phone)
values (2, 'international@internationalsos.org', 'Simon', 'mr Simon', '+987654321') ;
INSERT into contact_info (contact_info_id, contact_email, contact_job_title, contact_person, contact_phone)
values (3, 'testcountry@msf.org', 'tester', 'mr tester', '+6789') ;
INSERT into contact_info (contact_info_id, contact_email, contact_job_title, contact_person, contact_phone)
values (4, 'hello@hellosos.org', 'hello', 'mr hello', '+1234') ;

/* 5.Insert data for function standard*/
INSERT into function_info (function_info_id, function_name, level)
values (11, 'cleaner', 1) ;
INSERT into function_info (function_info_id, function_name, level)
values (12, 'HR Coordinator', 13) ;

/* 6.Insert data for function custom payment*/
INSERT into function_salary_info (function_salary_info_id, function_custom_name, basic_salary, tgc, monthly_allowance, org_id, function_info_id)
values (1, 'cleaner', 1000, 2000, 500,  1, 11) ;
INSERT into function_salary_info (function_salary_info_id, function_custom_name, basic_salary, tgc, monthly_allowance, org_id, function_info_id)
values (2, 'HR Coordinator', 10000, 12020, 2020,  1, 12) ;
INSERT into function_salary_info (function_salary_info_id, function_custom_name, basic_salary, tgc, monthly_allowance, org_id, function_info_id)
values (3, 'cleaning staff', 5000, 10000, 5000,  2, 11) ;
INSERT into function_salary_info (function_salary_info_id, function_custom_name, basic_salary, tgc, monthly_allowance, org_id, function_info_id)
values (4, 'Coordinator HR', 10002, 15020, 5020,  2, 12) ;
INSERT into function_salary_info (function_salary_info_id, function_custom_name, basic_salary, tgc, monthly_allowance, org_id, function_info_id)
values (5, 'Zanitor', 3000, 3500, 500,  4, 11) ;
INSERT into function_salary_info (function_salary_info_id, function_custom_name, basic_salary, tgc, monthly_allowance, org_id, function_info_id)
values (6, 'HR Director', 7002, 7502, 502,  4, 12) ;

/* 7.Insert data for Allowance*/
INSERT into allowance_info
values (1, 0, 300, 500,  800, 0) ;
INSERT into allowance_info
values (2, null, 300, 400,  700, 0) ;
INSERT into allowance_info
values (3, 0,  300, 500,  800, 0) ;
INSERT into allowance_info
values (4, 50, 100, 0, 200, 50) ;

/*8.Insert data for Allowance percent*/
INSERT into allowance_percent_info
values (1, 0.01, 0, 0, 0.02, 0.01) ;
INSERT into allowance_percent_info
values (2, 0.11, 0, 0.02, 0.18, 0.05) ;
INSERT into allowance_percent_info
values (3, 0.01, 0, 0, 0.02, 0.01) ;
INSERT into allowance_percent_info
values (4, 0, 0.2, 0.3, 0.5, 0) ;

CREATE TABLE IF NOT EXISTS police_crime_records (
time_period string,
victim_age string,
crime_type string,
data_measure string,
offence_count long
)
COMMENT 'Police Crime Records'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
STORED AS TEXTFILE
LOCATION '/user/hadoop/police_crime_record'
TBLPROPERTIES ("skip.header.line.count"="1");



CREATE TABLE IF NOT EXISTS deprivation (
ward string,
ward_code string,
multiple_deprivation_measure_rank int,
income_domain_rank int,
employment_domain_rank_18_59_64_years int,
health_deprivation_and_disability_domain_rank int,
education_skills_and_training_domain_rank int,
proximity_to_services_domain_rank int,
living_environment_domain_rank int,
crime_and_disorder_domain_rank int,
population_estimate_nimdm_2010 int
)
COMMENT 'Deprivation'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
STORED AS TEXTFILE
LOCATION '/user/hadoop/deprivation';


CREATE TABLE IF NOT EXISTS education (
pupils_school_management_type_2009_10 string,
controlled string,
catholic_maintained string,
other_maintained_irish_medium string,
other_maintained_other string,
controlled_integrated string,
grant_maintained_integrated string,
voluntary_schools_under_catholic_management string,
voluntary_schools_under_other_management string,
non_grant_aided string,
voluntary_pre_school_centres string,
private_pre_school_centres string,
grand_total string
)
COMMENT 'Education'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
STORED AS TEXTFILE
LOCATION '/user/hadoop/education';

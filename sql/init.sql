create database data_analytics;
CREATE USER 'da_user'@'localhost' IDENTIFIED BY 'DA1234';
GRANT ALL PRIVILEGES ON data_analytics. * TO 'da_user'@'localhost';
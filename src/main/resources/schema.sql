CREATE TABLE IF NOT EXISTS `user` (
    `id` int AUTO_INCREMENT PRIMARY KEY,
    `username` varchar(100) NOT NULL,
    `password` varchar(256) NOT NULL,
    `email` varchar(100) NOT NULL,
    `created_at` TIMESTAMP DEFAULT NULL,
    `updated_at` TIMESTAMP DEFAULT NULL
);
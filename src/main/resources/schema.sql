CREATE TABLE IF NOT EXISTS `users` (
    `id` int AUTO_INCREMENT PRIMARY KEY,
    `username` varchar(100) NOT NULL,
    `password` varchar(256) NOT NULL,
    `email` varchar(100) NOT NULL,
    `created_at` TIMESTAMP DEFAULT NULL,
    `update_at` TIMESTAMP DEFAULT NULL
);
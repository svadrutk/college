SELECT COUNT(*) AS num_sellers_higher_rating FROM Users AS u JOIN Sellers as s ON u.UserID = s.UserID WHERE u.Rating > 1000;
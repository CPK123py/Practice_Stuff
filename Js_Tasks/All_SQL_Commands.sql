-- Q1: User Upcoming Events
SELECT e.title, e.city, e.start_date, e.end_date
FROM Registrations r
JOIN Events e ON r.event_id = e.event_id
JOIN Users u ON r.user_id = u.user_id
WHERE e.status = 'upcoming'
  AND u.city = e.city
  AND r.user_id = ?  -- replace with target user_id
ORDER BY e.start_date ASC;

-- Q2: Top Rated Events
SELECT e.title, ROUND(AVG(f.rating), 2) AS avg_rating,
       COUNT(f.feedback_id) AS total_feedback
FROM Feedback f
JOIN Events e ON f.event_id = e.event_id
GROUP BY f.event_id, e.title
HAVING COUNT(f.feedback_id) >= 10
ORDER BY avg_rating DESC;

-- Q3: Inactive Users
SELECT u.user_id, u.full_name, u.email
FROM Users u
WHERE u.user_id NOT IN (
  SELECT DISTINCT user_id
  FROM Registrations
  WHERE registration_date >= CURDATE() - INTERVAL 90 DAY
);

-- Q4: Peak Session Hours
SELECT e.title, COUNT(s.session_id) AS peak_sessions
FROM Sessions s
JOIN Events e ON s.event_id = e.event_id
WHERE TIME(s.start_time) >= '10:00:00'
  AND TIME(s.start_time) < '12:00:00'
GROUP BY s.event_id, e.title;

-- Q5: Most Active Cities
SELECT u.city, COUNT(DISTINCT r.user_id) AS distinct_users
FROM Registrations r
JOIN Users u ON r.user_id = u.user_id
GROUP BY u.city
ORDER BY distinct_users DESC
LIMIT 5;

-- Q6: Event Resource Summary
SELECT e.title,
  SUM(CASE WHEN r.resource_type = 'pdf'   THEN 1 ELSE 0 END) AS pdfs,
  SUM(CASE WHEN r.resource_type = 'image' THEN 1 ELSE 0 END) AS images,
  SUM(CASE WHEN r.resource_type = 'link'  THEN 1 ELSE 0 END) AS links,
  COUNT(r.resource_id) AS total
FROM Events e
LEFT JOIN Resources r ON e.event_id = r.event_id
GROUP BY e.event_id, e.title;

-- Q7: Low Feedback Alerts
SELECT u.full_name, u.email, e.title AS event_name,
       f.rating, f.comments
FROM Feedback f
JOIN Users u  ON f.user_id  = u.user_id
JOIN Events e ON f.event_id = e.event_id
WHERE f.rating < 3
ORDER BY f.rating ASC;

-- Q8: Sessions per Upcoming Event
SELECT e.title, e.start_date,
       COUNT(s.session_id) AS session_count
FROM Events e
LEFT JOIN Sessions s ON e.event_id = s.event_id
WHERE e.status = 'upcoming'
GROUP BY e.event_id, e.title, e.start_date
ORDER BY e.start_date;

-- Q9: Organizer Event Summary
SELECT u.full_name AS organizer,
  COUNT(e.event_id) AS total_events,
  SUM(CASE WHEN e.status = 'upcoming'   THEN 1 ELSE 0 END) AS upcoming,
  SUM(CASE WHEN e.status = 'completed'  THEN 1 ELSE 0 END) AS completed,
  SUM(CASE WHEN e.status = 'cancelled'  THEN 1 ELSE 0 END) AS cancelled
FROM Users u
JOIN Events e ON u.user_id = e.organizer_id
GROUP BY u.user_id, u.full_name;

-- Q10: Feedback Gap
SELECT e.event_id, e.title
FROM Events e
WHERE e.event_id IN (
    SELECT DISTINCT event_id FROM Registrations
)
AND e.event_id NOT IN (
    SELECT DISTINCT event_id FROM Feedback
);

-- Q11: Daily New User Count
SELECT registration_date, COUNT(*) AS new_users
FROM Users
WHERE registration_date >= CURDATE() - INTERVAL 7 DAY
GROUP BY registration_date
ORDER BY registration_date DESC;

-- Q12: Event with Maximum Sessions
SELECT e.title, COUNT(s.session_id) AS session_count
FROM Events e
JOIN Sessions s ON e.event_id = s.event_id
GROUP BY e.event_id, e.title
HAVING COUNT(s.session_id) = (
  SELECT MAX(cnt) FROM (
    SELECT COUNT(session_id) AS cnt
    FROM Sessions
    GROUP BY event_id
  ) AS sub
);

-- Q13: Average Rating per City
SELECT e.city, ROUND(AVG(f.rating), 2) AS avg_rating
FROM Feedback f
JOIN Events e ON f.event_id = e.event_id
GROUP BY e.city
ORDER BY avg_rating DESC;

-- Q14: Most Registered Events
SELECT e.title, COUNT(r.registration_id) AS total_registrations
FROM Registrations r
JOIN Events e ON r.event_id = e.event_id
GROUP BY r.event_id, e.title
ORDER BY total_registrations DESC
LIMIT 3;

-- Q15: Event Session Time Conflict
SELECT a.session_id AS s1_id, a.title AS s1_title,
       b.session_id AS s2_id, b.title AS s2_title,
       a.event_id
FROM Sessions a
JOIN Sessions b
  ON  a.event_id   = b.event_id
  AND a.session_id < b.session_id
  AND a.start_time < b.end_time
  AND a.end_time   > b.start_time;

-- Q16: Unregistered Active Users
SELECT user_id, full_name, email, registration_date
FROM Users
WHERE registration_date >= CURDATE() - INTERVAL 30 DAY
  AND user_id NOT IN (
    SELECT DISTINCT user_id FROM Registrations
  );

-- Q17: Multi-Session Speakers
SELECT speaker_name, COUNT(session_id) AS session_count
FROM Sessions
GROUP BY speaker_name
HAVING COUNT(session_id) > 1
ORDER BY session_count DESC;

-- Q18: Resource Availability Check
SELECT e.event_id, e.title
FROM Events e
WHERE e.event_id NOT IN (
  SELECT DISTINCT event_id FROM Resources
);

-- Q19: Completed Events with Feedback Summary
SELECT e.title,
  COUNT(DISTINCT r.registration_id) AS total_registrations,
  ROUND(AVG(f.rating), 2)           AS avg_rating
FROM Events e
LEFT JOIN Registrations r ON e.event_id = r.event_id
LEFT JOIN Feedback f      ON e.event_id = f.event_id
WHERE e.status = 'completed'
GROUP BY e.event_id, e.title;

-- Q20: User Engagement Index
SELECT u.user_id, u.full_name,
  COUNT(DISTINCT r.event_id)    AS events_registered,
  COUNT(DISTINCT f.feedback_id) AS feedbacks_given
FROM Users u
LEFT JOIN Registrations r ON u.user_id = r.user_id
LEFT JOIN Feedback f      ON u.user_id = f.user_id
GROUP BY u.user_id, u.full_name
ORDER BY events_registered DESC, feedbacks_given DESC;

-- Q21: Top Feedback Providers
SELECT u.full_name, COUNT(f.feedback_id) AS feedback_count
FROM Feedback f
JOIN Users u ON f.user_id = u.user_id
GROUP BY f.user_id, u.full_name
ORDER BY feedback_count DESC
LIMIT 5;

-- Q22: Duplicate Registrations Check
SELECT user_id, event_id,
       COUNT(registration_id) AS reg_count
FROM Registrations
GROUP BY user_id, event_id
HAVING COUNT(registration_id) > 1;

-- Q23: Registration Trends
SELECT
  DATE_FORMAT(registration_date, '%Y-%m') AS month,
  COUNT(*) AS registrations
FROM Registrations
WHERE registration_date >= CURDATE() - INTERVAL 12 MONTH
GROUP BY DATE_FORMAT(registration_date, '%Y-%m')
ORDER BY month ASC;

-- Q24: Average Session Duration per Event
SELECT e.title,
  ROUND(AVG(
    TIMESTAMPDIFF(MINUTE, s.start_time, s.end_time)
  ), 2) AS avg_duration_mins
FROM Sessions s
JOIN Events e ON s.event_id = e.event_id
GROUP BY s.event_id, e.title;

-- Q25: Events Without Sessions
SELECT e.event_id, e.title, e.status
FROM Events e
WHERE e.event_id NOT IN (
  SELECT DISTINCT event_id FROM Sessions
);
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accepted Follow Card</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>

        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: #f5f7fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
        }

        .follow-card {
            background-color: #ffffff;
            padding: 40px 50px;
            border-radius: 20px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
            text-align: center;
            max-width: 400px;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .follow-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.15);
        }

        h1 {
            margin: 0;
            font-size: 2rem;
            color: #333333;
        }

        h2 {
            margin: 10px 0 20px 0;
            font-size: 1.2rem;
            font-weight: 500;
            color: #555555;
        }

        h3 {
            margin: 0;
            font-size: 1rem;
            font-weight: 400;
            color: #777777;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 8px;
        }

        h3 i {
            color: #00a4ff; /* clock icon color */
        }
    </style>
</head>
<body>
<div class="follow-card">
    <h1>${lastname} ${firstname}</h1>
    <h2>accepted to join your network</h2>
    <h3><i class="fa-regular fa-clock"></i> ${timePassed}</h3>
</div>
</body>
</html>
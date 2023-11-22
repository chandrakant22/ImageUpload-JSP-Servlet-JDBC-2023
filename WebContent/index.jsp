<!DOCTYPE html>
<html>
<head>
    <title>Image Upload and Display</title>
</head>
<body>
    <h2>Image Upload and Display</h2>
    
    <form action="UploadServlet" method="post" enctype="multipart/form-data">
        <label for="file">Choose Image:</label>
        <input type="file" name="file" id="file" accept="image/*" required>
        <br>
        <input type="submit" value="Upload">
    </form>

</body>
</html>

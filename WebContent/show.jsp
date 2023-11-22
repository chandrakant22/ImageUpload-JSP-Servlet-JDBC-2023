<!DOCTYPE html>
<html>
<head>
    <title>Image Upload and Display</title>
</head>
<body>

    <h2>Image Gallery</h2>
    <table border="1">
        <tr>
            <th>Image</th>
        </tr>
        <tr>
            <td><img src="uploads/<% String img= request.getParameter("img");
            System.out.print(img);
            if(img==null)
            {
            	out.print("default.jpg");
            	System.out.print("if");
            }
            	else
            	{
            		out.print(img);
            		System.out.print("else");
            	}
            %>" alt="Default Image" width="100"></td>
        </tr>
    </table>
</body>
</html>

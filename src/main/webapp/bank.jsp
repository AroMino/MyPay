<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Document</title>
</head>
<body>

    <div class="box">
        <h1 class="title">Pay :</h1>
        <h1 class="value"><%= (int)request.getAttribute("cur_pay")+" Ar" %></h1>
    </div>
    
    <form action="">
        <select name="type" class="type">
            <option value="deposit">Bank deposit</option>
            <option value="withdrawal">Bank withdrawal</option>
            <option value="transfer_to">Bank transfer to</option>
            <option value="transfer_from">Bank transfer from</option>
            <option value="payment">Bank payment</option>
        </select>
        <input type="text" name="motive" id="motive" placeholder="Motive">
        <input type="text" name="src_des" id="src_des" placeholder="For">
        <input type="text" name="details" id="details" placeholder="Details">
        <input type="number" name="value" id="details" placeholder="Value">
        <input type="hidden" name="login" value="<%= (String) request.getAttribute("login") %>">
        <button>Submit</button>
        <a class="back" href="View?login=<%= (String) request.getAttribute("login") %>">Back</a>
        
    </form>
    <script>
        const select = document.querySelector(".type");
        select.value = "deposit";
		document.querySelector("#details").style.display = "none";
        document.querySelector("#src_des").style.display = "none";
        select.addEventListener("change",(e) => {
            const value = select.value;
            if(value === "deposit" || value === "withdrawal"){
                document.querySelector("#details").style.display = "none";
                document.querySelector("#src_des").style.display = "none";

            }
            else{
                document.querySelector("#src_des").style.display = "block";
                document.querySelector("#src_des").placeholder = "For";
                document.querySelector("#details").style.display = "block";
            }
            if(value === "transfer_to"){
                document.querySelector("#src_des").placeholder = "For";
            }
            else if(value === "transfer_from"){
                document.querySelector("#src_des").placeholder = "From";
            }
            else if(value === "payment"){
                document.querySelector("#src_des").placeholder = "For";
            }
        });
    </script>
</body>
</html>
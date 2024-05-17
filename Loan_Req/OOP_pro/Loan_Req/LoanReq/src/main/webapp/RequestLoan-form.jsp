<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Loan Management Application</title>
    <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
    <style>
        body {
            background-image : url('https://img.freepik.com/premium-photo/top-view-frame-with-document-cash_23-2149103960.jpg?w=740');
            background-size : cover;
            background-color: #f4f4f4;
            font-family: Arial, sans-serif;
        }

        header {
            background-color: #333;
            padding: 10px;
            color: #fff;
        }

        .card {
            background-color:#cccccc;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            padding: 20px;
        }

        label {
            font-weight: bold;
            color: #333;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .btn {
            background-color: #4CAF50;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .btn:hover {
            background-color: #45a049;
        }

        .back-link {
            text-decoration: none;
            color: #007BFF;
            margin-top: 10px;
            display: inline-block;
        }

        .back-link:hover {
            text-decoration: underline;
        }

        .error-message {
            color: #FF0000;
        }
        
        .footer{
    		position: relative;
    		top: 0px;
    		margin: 0;
    		height: 300px;
    		background-color: rgb(2, 2, 56);
    		background-image: linear-gradient(rgb(8, 0, 51),rgb(0, 0, 0));
		}
		.footer{
			background-color: #24262b;
    		padding: 70px 0;
		}
		.footer-col{
  			width: 20%;
   			padding: 0 15px;
		}
		.footer-col h4{
			font-size: 18px;
			color: #ffffff;
			text-transform: capitalize;
			margin-bottom: 35px;
			font-weight: 500;
			position: relative;
		}
		.footer-col h4::before{
			content: '';
			position: absolute;
			left:0;
			bottom: -10px;
			background-color: #e91e63;
			height: 2px;
			box-sizing: border-box;
			width: 50px;
		}
		.footer-col ul li:not(:last-child){
			margin-bottom: 10px;
		}
		.footer-col ul li a{
			font-size: 16px;
			text-transform: capitalize;
			color: #ffffff;
			text-decoration: none;
			font-weight: 300;
			color: #bbbbbb;
			display: block;
			transition: all 0.3s ease;
		}
		.footer-col ul li a:hover{
			color: #ffffff;
			padding-left: 8px;
		}
		.footer-col .social-links a{
			display: inline-block;
			height: 40px;
			width: 40px;
			background-color: rgba(255,255,255,0.2);
			margin:0 10px 10px 0;
			text-align: center;
			line-height: 40px;
			border-radius: 50%;
			color: #ffffff;
			transition: all 0.5s ease;
		}
		.footer-col .social-links a:hover{
			color: #24262b;
			background-color: #ffffff;
		}        
	    </style>
    <script>
        function validateForm() {
            var name = document.forms["loanForm"]["name"].value;
            var accountNumber = document.forms["loanForm"]["accountNumber"].value;
            var phoneNumber = document.forms["loanForm"]["phoneNumber"].value;
            var email = document.forms["loanForm"]["email"].value;
            var photo = document.forms["loanForm"]["photo"].value;
            var nic = document.forms["loanForm"]["nic"].value;

            // Regular expressions for account number and phone number
            var accountNumberPattern = /^\d{5}$/; // Change to match your account number format
            var phoneNumberPattern = /^\d{10}$/; // Change to match your phone number format

            // Perform validation
            if (name === "" || !accountNumber.match(accountNumberPattern) || !phoneNumber.match(phoneNumberPattern) || email === "" || photo === "" || nic === "") {
                if (name === "") {
                    document.getElementById("name-error").innerText = "Name is required.";
                } else {
                    document.getElementById("name-error").innerText = "";
                }

                if (!accountNumber.match(accountNumberPattern)) {
                    document.getElementById("accountNumber-error").innerText = "Account Number is not valid.";
                } else {
                    document.getElementById("accountNumber-error").innerText = "";
                }

                if (!phoneNumber.match(phoneNumberPattern)) {
                    document.getElementById("phoneNumber-error").innerText = "Phone Number is not valid.";
                } else {
                    document.getElementById("phoneNumber-error").innerText = "";
                }

                if (email === "") {
                    document.getElementById("email-error").innerText = "Email is required.";
                } else {
                    document.getElementById("email-error").innerText = "";
                }

                if (photo === "") {
                    document.getElementById("photo-error").innerText = "Photo is required.";
                } else {
                    document.getElementById("photo-error").innerText = "";
                }

                if (nic === "") {
                    document.getElementById("nic-error").innerText = "NIC is required.";
                } else {
                    document.getElementById("nic-error").innerText = "";
                }

                alert("Please correct the errors before submitting the form.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-md navbar-dark">
            <div>
                <h3>Loan Management </h3>
            </div>
        </nav>
    </header>
    <br>
    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <form name="loanForm" action="<c:choose><c:when test='${application != null}'>update</c:when><c:otherwise>insert</c:otherwise></c:choose>" method="post" onsubmit="return validateForm()">
                    <caption>
                        <h2>
                            <c:choose>
                                <c:when test="${application != null}">
                                    Edit Loan
                                </c:when>
                                <c:otherwise>
                                    <div class="add-new-loan">Request A Loan</div>
                                </c:otherwise>
                            </c:choose>
                        </h2>
                    </caption>

                    <c:if test="${application != null}">
                        <input type="hidden" name="id" value="${application.id}" />
                    </c:if>

                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" value="${application.name}" class="form-control" name="name" id="name" required="required">
                        <span class="error-message" id="name-error"></span>
                    </div>

                    <div class="form-group">
                        <label for="accountNumber">Account Number</label>
                        <input type="text" value="${application.accountNumber}" class="form-control" name="accountNumber" id="accountNumber" required="required">
                        <span class="error-message" id="accountNumber-error"></span>
                    </div>

                    <div class="form-group">
                        <label for="phoneNumber">Phone Number</label>
                        <input type="text" value="${application.phoneNumber}" class="form-control" name="phoneNumber" id="phoneNumber" required="required">
                        <span class="error-message" id="phoneNumber-error"></span>
                    </div>

                    <div class="form-group">1
                        <label for="email">Email</label>
                        <input type="email" value="${application.email}" class="form-control" name="email" id="email" required="required">
                        <span class="error-message" id="email-error"></span>
                    </div>

                    <div class="form-group">
                        <label for="photo">Photo</label>
                        <input type="file" value="${application.photo}" class="form-control" name="photo" id="photo" required="required">
                        <span class="error-message" id="photo-error"></span>
                    </div>

                    <div class="form-group">
                        <label for="nic">NIC</label>
                        <input type="text" value="${application.nic}" class="form-control" name="nic" id="nic" required="required">
                        <span class="error-message" id="nic-error"></span>
                    </div>
                    
                    <div class="form-group">
                        <label for="type">Account Type</label>
                        <input type="text" value="${application.type}" class="form-control" name="type" id="type" required="required">
                        <span class="error-message" id="nic-error"></span>
                    </div>
                    
                    <div class="form-group">
                        <label for="amount">Loan Amount</label>
                        <input type="text" value="${application.amount}" class="form-control" name="amount" id="amount" required="required">
                        <span class="error-message" id="nic-error"></span>
                    </div>
                    
                    <div class="form-group">
                        <label for="type">Years</label>
                        <input type="text" value="${application.years}" class="form-control" name="years" id="years" required="required">
                        <span class="error-message" id="nic-error"></span>
                    </div>
                    
                    <button type="submit" class="btn btn-success">Save</button>
                    
                    <a class= "back-link" href="http://localhost:8090/LoanReq/list"> View List</a>
                </form>
            </div>
        </div>
      <section class="footer">

		<div class="container">
			<div class="row">
				<div class="footer-col">
					<h4>company</h4>
					<ul>
						<li><a href="#">Home</a></li>
						<li><a href="#">our services</a></li>
						<li><a href="#">privacy policy</a></li>
						<li><a href="#">Contact Us</a></li>
					</ul>
				</div>
				<div class="footer-col">
					<h4>get help</h4>
					<ul>
						<li><a href="#">FAQ</a></li>

					</ul>
				</div>
				<div class="footer-col">
					<h4>online Banking</h4>
					<ul>
						<li><a href="#">Online Banking Portal</a></li>
						<li><a href="#">Help</a></li>

					</ul>
				</div>
				<div class="footer-col">
					<h4>follow us</h4>
					<div class="social-links">
						<a  href="#"><img src="images/facebook (1).svg" alt=""></a> 
						<a	href="#"><img src="images/instagram.svg" alt=""></a> 
						<a	href="#"><img src="images/linkedin.svg" alt=""></a> 
						<a	href="#"><img src="images/twitter.svg" alt=""></a>
					</div>
				</div>
			</div>
		</div>


	</section>
    </div>
</body>
</html>

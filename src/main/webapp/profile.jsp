<%@ page import="project.model.Customer, project.dao.AccountDAO" %>
<%
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("login.jsp");
        return;
    }
	AccountDAO accountDao = new AccountDAO();
    double totalBalance = accountDao.getTotalBalance(customer.getCustomerId());
    long totalAccounts = accountDao.getTotalAccounts(customer.getCustomerId());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Profile</title>
<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link
    href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
    rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/style.min.css" rel="stylesheet">
</head>
<body class="bg-gradient-primary">
<section>
    <div class="container py-5">
        <div class="row">
            <!-- Profile Section -->
            <div class="col-lg-4">
                <div class="card mb-4">
                    <div class="card-body text-center">
                    <img class="rounded-circle img-fluid" style="width: 150px;"
                                    src="img/undraw_profile.svg">
                        <h5 class="my-3"><%= customer.getCustomerName() %></h5>
                        <p class="text-muted mb-1"><%= customer.getCustomerEmail() %></p>
                        <p class="text-muted mb-4">Customer Profile</p>
                    </div>
                </div>
            </div>

            <div class="col-lg-8">
                <div class="card mb-4">
                    <div class="card-body">
                        <form action="UpdateCustomerServlet" method="post">
                            <!-- Form Fields -->
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Full Name</p>
                                </div>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="customerName" value="<%= customer.getCustomerName() %>" readonly>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Email</p>
                                </div>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" name="customerEmail" value="<%= customer.getCustomerEmail() %>" readonly>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Income</p>
                                </div>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="customerIncome" value="<%= customer.getCustomerIncomce() %>" required>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Age</p>
                                </div>
                                <div class="col-sm-9">
                                    <input type="number" class="form-control" name="customerAge" value="<%= customer.getCustomerAge() %>" required>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-6">
                                    <button type="submit" class="btn btn-primary">Update Profile</button>
                                    <a href="index.jsp" class="btn btn-outline-primary">Back</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6">
                <div class="card mb-4">
                    <div class="card-body text-center">
                        <h5 class="my-3">Total Balance</h5>
                        <p class="h4 text-primary"><%= String.format("Rs. %.2f", totalBalance) %></p>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="card mb-4">
                    <div class="card-body text-center">
                        <h5 class="my-3">Total Accounts</h5>
                        <p class="h4 text-primary"><%= totalAccounts %></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>


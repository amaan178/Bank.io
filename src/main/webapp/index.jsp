<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page
	import="project.dao.*, project.model.Customer,java.util.* ,project.model.Account, java.text.*,com.google.gson.Gson"%>
<%
    session = request.getSession(false);
	Customer customer = null;
	
	if (session != null) {
	    customer = (Customer) session.getAttribute("customer");
	}

	if (customer == null) {
	    response.sendRedirect("login.jsp");
	    return;
	}
%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Bank.io - Dashboard</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/style.min.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">Bank.io <sup>2</sup></div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="index.jsp">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Dashboard</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Banking System
            </div>

            <!-- Nav Item  -->
            <li class="nav-item">
                <a class="nav-link" href="accounts.jsp">
                    <i class="fas fa-user"></i>
                    <span>Accounts</span></a>
            </li>

            <!-- Nav Item -->
            <li class="nav-item">
                <a class="nav-link" href="transactions.jsp">
                    <i class="fas fa-money-bill"></i>
                    <span>Transactions</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Loan Management
            </div>

            <!-- Nav Item - Pages Collapse Menu -->

            <!-- Nav Item - Charts -->
            <li class="nav-item">
                <a class="nav-link" href="loans.jsp">
                    <i class="fas fa-fw fa-landmark "></i>
                    <span>Loans</span></a>
            </li>

            <!-- Nav Item - Tables -->
            <li class="nav-item">
                <a class="nav-link" href="loanCalculator.jsp">
                    <i class="fas fa-fw fa-calculator"></i>
                    <span>Loan Calculator</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    <!-- Topbar Search -->
                    <form
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                        <div class="input-group">
                            <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                                aria-label="Search" aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-primary" type="button">
                                    <i class="fas fa-search fa-sm"></i>
                                </button>
                            </div>
                        </div>
                    </form>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>


                        <!-- Nav Item - Messages -->
						<%
						    int customerId = customer.getCustomerId();
						
						    // Initialize AccountDAO
						    AccountDAO accountDAO = new AccountDAO();
						
						    // Get total balance and number of accounts
						    double totalBalance = accountDAO.getTotalBalance(customerId);
						    long totalAccounts = accountDAO.getTotalAccounts(customerId);
						%>
						<!-- Navigation Bar with Dropdown -->
						<li class="nav-item dropdown no-arrow mx-1">
						    <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						        <i class="fas fa-wallet"></i>
						    </a>
						    <!-- Dropdown - Wallet Info -->
						    <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="messagesDropdown">
						        <h6 class="dropdown-header">
						            Wallet Info
						        </h6>
						        <a class="dropdown-item d-flex align-items-center" href="#">
						            <div class="font-weight-bold">
						                <div class="text-truncate">Total Balance: Rs. <%= String.format("%.2f", totalBalance) %></div>
						                <div class="small text-gray-500">Total Accounts: <%= totalAccounts %></div>
						            </div>
						        </a>
						    </div>
						</li>


                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <% 
								    customer = (Customer) session.getAttribute("customer");
								    if (customer != null) {
								%>
								    <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%= customer.getCustomerName() %></span>
								<% 
								    } else {
								%>
								    <span class="mr-2 d-none d-lg-inline text-gray-600 small">Guest</span>
								<% 
								    }
								%>
                                <img class="img-profile rounded-circle"
                                    src="img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="profile.jsp">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
                    </div>

                    <!-- Content Row -->
                    <div class="row">
                         <!-- Earnings (Monthly) Card Example -->
                         <% 
	                         AccountDAO accountDao = new AccountDAO();
	                         LoanDAO loanDao = new LoanDAO();
	                         int totalEarningsAnnual = (int) customer.getCustomerIncomce();
	                         int totalEarningsMonthly = (int) (totalEarningsAnnual / 12.0);
	                         int totalLoans = (int) loanDao.getTotalLoans(customer.getCustomerId());
                         %>
					    <div class="col-xl-3 col-md-6 mb-4">
					        <div class="card border-left-primary shadow h-100 py-2">
					            <div class="card-body">
					                <div class="row no-gutters align-items-center">
					                    <div class="col mr-2">
					                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
					                            Earnings (Monthly)</div>
					                        <div class="h5 mb-0 font-weight-bold text-gray-800">
					                            ₹<%= totalEarningsMonthly %>
					                        </div>
					                    </div>
					                    <div class="col-auto">
					                        <i class="fas fa-rupee-sign fa-2x text-gray-300"></i>
					                    </div>
					                </div>
					            </div>
					        </div>
					    </div>
					
					    <!-- Earnings (Annual) Card Example -->
					    <div class="col-xl-3 col-md-6 mb-4">
					        <div class="card border-left-success shadow h-100 py-2">
					            <div class="card-body">
					                <div class="row no-gutters align-items-center">
					                    <div class="col mr-2">
					                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
					                            Earnings (Annual)</div>
					                        <div class="h5 mb-0 font-weight-bold text-gray-800">
					                            ₹<%= totalEarningsAnnual %>
					                        </div>
					                    </div>
					                    <div class="col-auto">
					                        <i class="fas fa-piggy-bank fa-2x text-gray-300"></i>
					                    </div>
					                </div>
					            </div>
					        </div>
					    </div>
					
					    <!-- Total Accounts Card Example -->
					    <div class="col-xl-3 col-md-6 mb-4">
					        <div class="card border-left-danger shadow h-100 py-2">
					            <div class="card-body">
					                <div class="row no-gutters align-items-center">
					                    <div class="col mr-2">
					                        <div class="text-xs font-weight-bold text-danger text-uppercase mb-1">
					                            Total Accounts</div>
					                        <div class="h5 mb-0 font-weight-bold text-gray-800">
					                            <%= totalAccounts %>
					                        </div>
					                    </div>
					                    <div class="col-auto">
					                        <i class="fas fa-users fa-2x text-gray-300"></i>
					                    </div>
					                </div>
					            </div>
					        </div>
					    </div>
					
					    <!-- Total Loans Card Example -->
					    <div class="col-xl-3 col-md-6 mb-4">
					        <div class="card border-left-warning shadow h-100 py-2">
					            <div class="card-body">
					                <div class="row no-gutters align-items-center">
					                    <div class="col mr-2">
					                        <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
					                            Total Active Loans</div>
					                        <div class="h5 mb-0 font-weight-bold text-gray-800">
					                            <%= totalLoans %>
					                        </div>
					                    </div>
					                    <div class="col-auto">
					                        <i class="fas fa-money-bill fa-2x text-gray-300"></i>
					                    </div>
					                </div>
					            </div>
					        </div>
					    </div>                      
                    </div>

                    <!-- Content Row -->

                    <div class="row">
					    <div class="col-xl-8 col-lg-7">
					        <div class="card shadow mb-4">
					            <!-- Card Header - Dropdown -->
					            <% 
									TransactionsDAO transactionsDao = new TransactionsDAO();
									double deposit = transactionsDao.getTotalAmount(customer.getCustomerId(), "Deposit");
									double withdraw = transactionsDao.getTotalAmount(customer.getCustomerId(), "Withdraw");
									double loanEmi = transactionsDao.getTotalAmount(customer.getCustomerId(), "Loan EMI");
									double[] data = {deposit, withdraw, loanEmi};
									Gson gson = new Gson();
									String dataValues2 = gson.toJson(data);
									System.out.println(deposit);
									System.out.println(withdraw);
									System.out.println(loanEmi);
								%>
					            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
					                <h6 class="m-0 font-weight-bold text-primary">Transactions Overview</h6>
					            </div>
					            <!-- Card Body -->
					            <div class="card-body">
					                <div class="chart-area">
					                    <canvas id="myHistoChart" data-values="<%= dataValues2 %>"></canvas>
					                </div>
					            </div>
					        </div>
					    </div>

                        <!-- Pie Chart -->
                        <div class="col-xl-4 col-lg-5">
                            <div class="card shadow mb-4">
                                <!-- Card Header - Dropdown -->
                                <%
									List<Account> accounts = accountDao.getCustAccount(customer.getCustomerId());
									String[] labelsNames = new String[accounts.size()];
									int[] dataValues = new int[accounts.size()];
	
									// Define a fixed set of colors
									String[] backgroundColors = new String[] { "#4e73df", "#1cc88a", "#36b9cc", "#f6c23e", "#e74a3b", "#5a5c69", "#d1d3e2",
											"#8c9c1e" };
									String[] hoverBackgroundColors = new String[] { "#2e59d9", "#17a673", "#2c9faf", "#f4b619", "#e14a3b", "#464a5e",
											"#b1b9c5", "#7a8c0d" };
	
									// Handle case where there are more accounts than colors available
									ArrayList<String> finalBackgroundColors = new ArrayList<>();
									ArrayList<String> finalHoverBackgroundColors = new ArrayList<>();
	
									for (int i = 0; i < accounts.size(); i++) {
										labelsNames[i] = accounts.get(i).getBankName();
										dataValues[i] = (int)accounts.get(i).getAccountBalance();
										// Assign colors, cycling if necessary
										finalBackgroundColors.add(backgroundColors[i % backgroundColors.length]);
										finalHoverBackgroundColors.add(hoverBackgroundColors[i % hoverBackgroundColors.length]);
									}
									String labelsJson = gson.toJson(labelsNames);
									String dataJson = gson.toJson(dataValues);
									String backgroundColorsJson = gson.toJson(finalBackgroundColors);
									String hoverBackgroundColorsJson = gson.toJson(finalHoverBackgroundColors);
								%>
                                <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                    <h6 class="m-0 font-weight-bold text-primary">Total Accounts and their Balance</h6>
                                </div>
                                <!-- Card Body -->
                                <div class="card-body">
                                    <div class="chart-pie pt-4 pb-2">
                                        <canvas id='myPieChart' data-labelNames='<%= labelsJson %>' data-dataValues='<%= dataJson %>' data-backgroundColors='<%= backgroundColorsJson %>' data-hoverBackgroundColors='<%= hoverBackgroundColorsJson %>'></canvas>
                                    </div>
                                    <div class="mt-4 text-center small">
									<% for (int i = 0; i < accounts.size(); i++) {%>
										<span class="mr-2"> 
											<i class="fas fa-circle" style="color: <%= finalBackgroundColors.get(i) %>"></i> <%= labelsNames[i] %>
										</span>
									<% } %>
									</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Montran Project Bankio 2024</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">X</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <form action="LogoutCustomer" method="get">
					    <button class="btn btn-primary" type="submit">Logout</button>
					</form>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/script.min.js"></script>

	<!-- Page level plugins -->
	<script src="vendor/chart.js/Chart.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="js/demo/chart-histo-demo.js"></script>
	<script src="js/demo/chart-pie-demo.js"></script>

</body>

</html>
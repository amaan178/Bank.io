<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="project.model.*, project.dao.AccountDAO" %>
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

    <title>Bank.io - Loans</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/style.min.css" rel="stylesheet">
    
    <style>
	    .loanTypeCard {
	        transition: transform 0.2s ease-in-out;
	    }
	
	    .loanTypeCard:hover {
	        transform: scale(1.03);
    		box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
	    }
	</style>

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.jsp">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">Bank.io <sup>2</sup></div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item">
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
            <li class="nav-item active">
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

                        <%
						    int customerId = customer.getCustomerId();
						    AccountDAO accountDAO = new AccountDAO();
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
                        <h1 class="h3 mb-0 text-gray-800">Loan Applications</h1>
                        <a href="loanApplication.jsp" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">Add Loan Application</a>
                    </div>

                    <!-- Content Row -->
                    <div class="row">
                        <!-- Home Loan Card -->
					    <div class="col-xl-6 col-md-6 mb-4">
					        <div class="card border-left-primary shadow h-100 py-2 loanTypeCard" onclick="location.href='loanApplication.jsp?loanType=Home'">
					            <div class="card-body">
					                <div class="row no-gutters align-items-center">
					                    <div class="col mr-2">
					                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Loan Type</div>
					                        <div class="h5 mb-0 font-weight-bold text-gray-800">Home Loan</div>
					                    </div>
					                    <div class="col-auto">
					                        <i class="fas fa-home fa-3x text-gray-300"></i>
					                    </div>
					                </div>
					            </div>
					        </div>
					    </div>
					
					    <!-- Car Loan Card -->
					    <div class="col-xl-6 col-md-6 mb-4">
					        <div class="card border-left-success shadow h-100 py-2 loanTypeCard" onclick="location.href='loanApplication.jsp?loanType=Car'">
					            <div class="card-body">
					                <div class="row no-gutters align-items-center">
					                    <div class="col mr-2">
					                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Loan Type</div>
					                        <div class="h5 mb-0 font-weight-bold text-gray-800">Car Loan</div>
					                    </div>
					                    <div class="col-auto">
					                        <i class="fas fa-car fa-3x text-gray-300"></i>
					                    </div>
					                </div>
					            </div>
					        </div>
					    </div>
					    
					</div>

                    
                    <!--Sub Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Active Loan Details</h1>
                    <p class="mb-4">Just Getting the Loan Details from the Database</p>
                    <%@ page import="project.dao.LoanDAO,java.util.* ,project.model.Loan, java.text.*" %>
                    <% 
	                    LoanDAO loanDao = new LoanDAO();
	                    List<Loan> loans = loanDao.getCustLoan(customer.getCustomerId());
	                    Locale locale = new Locale("en", "IN");
	                    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
                	 for (Loan loan: loans) { 
                		Account account = (Account) accountDAO.getLoanAccountBalance2(loan.getAccountId());
                 	 	if(account != null) {
                	 %> 
	                    <div class="card mt-3">
	                        <div class="row">
	                            <div class="col-xl-12 col-md-6 mb-2">
	                                <div class="card-body">
	                                    <div class="d-sm-flex align-items-center justify-content-between mb-3">
											<h5 class="h5 mb-0 text-gray-800">Loan Details</h5>
											<p class=" mb-0 text-gray-800">Account: <%= account.getBankName() %></p>
											<form action="PayLoanEmi" method="post">
												<input type="hidden" name="loanId" value="<%= loan.getLoanId()%>">
												<input type="hidden" name="loanPaidEmi" value="<%= loan.getLoanPaidEmi()%>">
												<input type="hidden" name="loanRemainEmi" value="<%= loan.getLoanRemainEmi()%>">
												<input type="hidden" name="loanNextEmiDate" value="<%= loan.getLoanNextEmiDate()%>">
												<input type="hidden" name="accountId" value="<%= loan.getAccountId()%>">
												<input type="hidden" name="loanEmiAmount" value="<%= loan.getLoanEmiAmount()%>">
												<% if(loan.getLoanStatus().equals("Open")) { %>
												<button
													class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">Pay Emi
												</button>
												<% } %>
											</form>
										</div>
	                                    <div class="row">
	                                        <div class="col-2">
	                                            <p class="text-m lh-base text-gray-500 mb-1">Loan Type</p>
	                                            <span class="text-m text-gray-800"><%= loan.getLoanType() %></span>
	                                        </div>
	                                        <div class="col-2">
	                                            <p class="text-m lh-base text-gray-500 mb-1">Loan Amount</p>
	                                            <span class="text-m text-gray-800">Rs. <%= loan.getLoanAmount() %></span>
	                                        </div>
	                                        <div class="col-2">
	                                            <p class="text-m lh-base text-gray-500 mb-1">Loan Status</p>
	                                            <span class="text-m text-gray-800"><%= loan.getLoanStatus() %></span>
	                                        </div>
	                                        <div class="col-2">
	                                            <p class="text-m lh-base text-gray-500 mb-1">Duration </p>
	                                            <span class="text-m text-gray-800"><%= loan.getLoanDuration() %> years</span>
	                                        </div>
	                                        <div class="col-2">
	                                            <p class="text-m lh-base text-gray-500 mb-1">Start Date</p>
	                                            <span class="text-m text-gray-800">
	                                            <% 
	                                                Date loanStartDate = loan.getLoanStartDate();
	                                                String startDateFormatted = (loanStartDate != null) ? dateFormat.format(loanStartDate) : "N/A";
	                                            %> 
	                                            <%= startDateFormatted %>
	                                            </span>
	                                        </div>
	                                        <div class="col-2">
	                                            <p class="text-m lh-base text-gray-500 mb-1">End Date</p>
	                                            <span class="text-m text-gray-800">
	                                            <% 
	                                                Date loanEndDate = loan.getLoanEndDate();
	                                                String endDateFormatted = (loanEndDate != null) ? dateFormat.format(loanEndDate) : "N/A";
	                                            %>
	                                            <%= endDateFormatted %>
	                                            </span>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                        <!-- Divider -->
	                        <hr class="sidebar-divider d-none d-md-block m-auto" width="90%">
	                        
	                        <div class="row">
	                            <div class="col-xl-12 col-md-6 mt-2 mb-2">
	                                <div class="card-body">
	                                    <h5 class="mb-3 font-weight-bold text-gray-800">EMI Details</h5>
	                                    <div class="row">
	                                        <div class="col-2">
	                                            <p class="text-m lh-base text-gray-500 mb-1">EMI Amount</p>
	                                            <span class="text-m text-primary font-weight-bold">Rs. <%= loan.getLoanEmiAmount() %></span>
	                                        </div>
	                                        <div class="col-2">
	                                            <p class="text-m lh-base text-gray-500 mb-1">Total EMI</p>
	                                            <span class="text-m text-gray-800"><%= loan.getLoanTotalEmi() %></span>
	                                        </div>
	                                        <div class="col-2">
	                                            <p class="text-m lh-base text-gray-500 mb-1">Paid EMI</p>
	                                            <span class="text-m text-success font-weight-bold"><%= loan.getLoanPaidEmi() %></span>
	                                        </div>
	                                        <div class="col-2">
	                                            <p class="text-m lh-base text-gray-500 mb-1">Remain EMI</p>
	                                            <span class="text-m text-warning font-weight-bold"><%= loan.getLoanRemainEmi() %></span>
	                                        </div>
	                                        <div class="col-2">
	                                            <p class="text-m lh-base text-gray-500 mb-1">Last EMI Date</p>
	                                            <span class="text-m text-gray-800">
	                                            <% 
	                                                Date lastEmiDate = loan.getLoanLastEmiDate();
	                                                String lastEmiDateFormatted = (lastEmiDate != null) ? dateFormat.format(lastEmiDate) : "N/A";
	                                            %>
	                                            <%= lastEmiDateFormatted %>
	                                            </span>
	                                        </div>
	                                        <div class="col-2">
	                                            <p class="text-m lh-base text-gray-500 mb-1">Next EMI Date</p>
	                                            <span class="text-m text-gray-800">
	                                            <% 
	                                                Date nextEmiDate = loan.getLoanNextEmiDate();
	                                                String nextEmiDateFormatted = (nextEmiDate != null) ? dateFormat.format(nextEmiDate) : "N/A";
	                                            %>
	                                            <%= nextEmiDateFormatted %>
	                                            </span>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                        <!-- Divider -->
	                        <hr class="sidebar-divider d-none d-md-block m-auto" width="90%">
	                        <div class="row">
	                            <div class="col-xl-12 col-md-6 mt-2">
	                                <div class="card-body">
	                                    <h5 class="mb-3 font-weight-bold text-gray-800">Loan Status</h5>
	                                    <div class="progress">
	                                    <% int percentage = (loan.getLoanTotalEmi() > 0) ? (loan.getLoanPaidEmi() * 100) / loan.getLoanTotalEmi() : 0;%>
	                                        <div class="progress-bar progress-bar-striped" role="progressbar" style="width: <%= percentage %>%" aria-valuenow="<%= percentage %>" aria-valuemin="0" aria-valuemax="100"><%= percentage %>%</div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                <% }} %>

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
    
    <div class="modal fade" id="errorModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Your Account Balance is insufficient !</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">X</span>
					</button>
				</div>
				<div class="modal-body">Select "Deposit" below if you want to add balance in your account.</div>
				<div class="modal-footer">
					<form action="loans.jsp" method="post">
						<button class="btn btn-secondary" type="submit">Cancel</button>
					</form>
					<form action="addNewTransaction.jsp" method="post">
						<button class="btn btn-primary" type="submit">Deposit</button>
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
	<script src="js/demo/EmiModal.js"></script>
    <!-- Page level plugins -->
    <script src="vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="js/demo/chart-area-demo.js"></script>
    <script src="js/demo/chart-pie-demo.js"></script>

</body>

</html>
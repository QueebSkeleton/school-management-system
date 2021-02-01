<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
  content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>School Management System - Admin | Dashboard</title>

<%-- Core Links --%>
<c:import url="../include/coreLinks.jsp"></c:import>

</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <%-- Sidebar --%>
    <c:import url="../include/sidebar.jsp"></c:import>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <%-- Topbar --%>
        <c:import url="../include/topbar.jsp"></c:import>

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <div
            class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Manage Instructors</h1>
            <button
              class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
              <i class="fas fa-plus fa-sm text-white-50"></i> Add
            </button>
          </div>

          <!-- Main Table -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">Instructors Table</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered">
                  <thead>
                    <tr>
                      <th>Name</th>
                      <th>Email Address</th>
                      <th>Action</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
                      <th>Name</th>
                      <th>Email Address</th>
                      <th>Action</th>
                    </tr>
                  </tfoot>
                  <tbody>
                    <tr>
                      <td>Tiger Nixon</td>
                      <td>tigernixon@schlmgmt.com</td>
                      <td>
                        <div class="btn-group">
                          <button class="btn btn-sm btn-primary">
                            <i class="fas fa-sm fa-edit"></i> Update
                          </button>
                          <button class="btn btn-sm btn-danger">
                            <i class="fas fa-sm fa-trash"></i> Delete
                          </button>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td>Adam Smith</td>
                      <td>adamsmith@schlmgmt.com</td>
                      <td>
                        <div class="btn-group">
                          <button class="btn btn-sm btn-primary">
                            <i class="fas fa-sm fa-edit"></i> Update
                          </button>
                          <button class="btn btn-sm btn-danger">
                            <i class="fas fa-sm fa-trash"></i> Delete
                          </button>
                        </div>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <%-- Footer --%>
      <c:import url="../include/footer.jsp"></c:import>

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <%-- Scroll to Top Button --%>
  <c:import url="../include/scrollToTop.jsp"></c:import>

  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
    aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to
            Leave?</h5>
          <button class="close" type="button" data-dismiss="modal"
            aria-label="Close">
            <span aria-hidden="true">Ã</span>
          </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are
          ready to end your current session.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button"
            data-dismiss="modal">Cancel</button>
          <a class="btn btn-primary" href="login.html">Logout</a>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <script
    src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script
    src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script
    src="${pageContext.request.contextPath}/admin/assets/js/sb-admin-2.min.js"></script>

</body>

</html>
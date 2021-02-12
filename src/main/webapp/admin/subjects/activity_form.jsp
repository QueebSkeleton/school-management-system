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

<title>
  School Management System - Admin | Add an Activity
</title>

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
            <h1 class="h3 mb-0 text-gray-800">Add or Update Activity</h1>
            <a href="${pageContext.request.contextPath}/admin/subjects/view.jsp?id=${param.subjectId}"
              class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
              <i class="fas fa-undo fa-sm text-white-50"></i> Back
            </a>
          </div>

          <!-- Subject Form -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">Add or Modify an Activity</h6>
            </div>
            <div class="card-body">
              <form method="POST" action="${param.subjectId != null ? 'activity_create' : ''}">
                <input type="hidden" name="id">
                
                <input type="hidden" name="subject.id" value="${param.subjectId}">
              
                <div class="form-group">
                  <label for="name">Name</label>
                  <input type="text" required name="name" class="form-control">
                </div>
              
                <div class="form-group">
                  <label for="code">Deadline</label>
                  <input type="datetime-local" required name="deadline" class="form-control">
                </div>
              
                <div class="form-group">
                  <label for="totalScore">Total Score</label>
                  <input type="number" required name="totalScore" class="form-control">
                </div>
                
                <button type="submit" class="btn btn-primary">
                  <i class="fas fa-sm fa-save"></i> Save
                </button>
              </form>
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
  
  <%-- Core Scripts --%>
  <c:import url="../include/coreScripts.jsp"></c:import>

</body>

</html>
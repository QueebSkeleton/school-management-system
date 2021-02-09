<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" isELIgnored="false"
  import="
  com.github.queebskeleton.schlmgmt.repository.Repository,
  com.github.queebskeleton.schlmgmt.domain.Subject"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- Wire dependencies from the ServletContext --%>
<%!private Repository<Subject, Integer> subjectRepository;

	@SuppressWarnings("unchecked")
	public void jspInit() {
		ServletContext servletContext = getServletContext();
		// Grab the subject repository object from the Servlet Context then inject it.
		subjectRepository = (Repository<Subject, Integer>) getServletContext().getAttribute("subjectRepository");
	}%>

<%-- Grab the associated subject with the given id from the request --%>
<c:set var="subject"
  value="${subjectRepository.getById(Integer.parseInt(param.id))}" />

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
  content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>School Management System - Admin | View</title>

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
            <h1 class="h3 mb-0 text-gray-800">View Subject</h1>
            <a
              href="${pageContext.request.contextPath}/admin/subjects/panel.jsp"
              class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
              <i class="fas fa-undo fa-sm text-white-50"></i> Back
            </a>
          </div>

          <div class="row">

            <!-- Main Subject Details Card -->
            <div class="col-md-6">
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">Details</h6>
                </div>
                <div class="card-body">
                  <div class="table-responsive">
                    <table class="table table-bordered">
                      <tbody>
                        <tr>
                          <th>Name</th>
                          <td>${subject.name}</td>
                        </tr>
                        <tr>
                          <th>Code</th>
                          <td>${subject.code}</td>
                        </tr>
                        <tr>
                          <th>Description</th>
                          <td>${subject.description}</td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
            <!-- END OF Subject Details Card -->

            <!-- Activities Card -->
            <div class="col-md-6">
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">Activities</h6>
                </div>
                <div class="card-body">
                  <div class="table-responsive">
                    <table class="table table-bordered">
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>Name</th>
                          <th>Deadline</th>
                          <th>Total Score</th>
                          <th>Actions</th>
                        </tr>
                      </thead>
                      <tfoot>
                        <tr>
                          <th>#</th>
                          <th>Name</th>
                          <th>Deadline</th>
                          <th>Total Score</th>
                          <th>Actions</th>
                        </tr>
                      </tfoot>
                      <tbody>
                        <c:forEach items="${subject.activities}" var="activity" varStatus="loop">
                          <tr>
                            <td>${loop.index + 1}</td>
                            <td>${activity.name}</td>
                            <td>${activity.deadline}</td>
                            <td>${activity.totalScore}</td>
                            <td></td>
                          </tr>
                        </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
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

  <%-- Core Scripts --%>
  <c:import url="../include/coreScripts.jsp"></c:import>

</body>

</html>
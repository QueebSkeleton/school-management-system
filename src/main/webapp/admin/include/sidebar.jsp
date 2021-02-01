<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" isELIgnored="false"%>
<!-- Sidebar -->
<ul
  class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
  id="accordionSidebar">

  <!-- Sidebar - Brand -->
  <a
    class="sidebar-brand d-flex align-items-center justify-content-center"
    href="${pageContext.request.contextPath}/admin/dashboard.jsp">
    <div class="sidebar-brand-icon rotate-n-15">
      <i class="fas fa-laugh-wink"></i>
    </div>
    <div class="sidebar-brand-text mx-3">
      SB Admin <sup>2</sup>
    </div>
  </a>

  <!-- Divider -->
  <hr class="sidebar-divider my-0">

  <!-- Nav Item - Dashboard -->
  <li class="nav-item"><a class="nav-link"
    href="${pageContext.request.contextPath}/admin/dashboard.jsp"> <i
      class="fas fa-fw fa-tachometer-alt"></i> <span>Dashboard</span>
  </a></li>

  <!-- Divider -->
  <hr class="sidebar-divider">

  <!-- Heading -->
  <div class="sidebar-heading">People</div>

  <!-- Nav Item - Instructors -->
  <li class="nav-item"><a class="nav-link"
    href="${pageContext.request.contextPath}/admin/instructors/panel.jsp">
      <i class="fas fa-fw fa-user-tie"></i> <span>Instructors</span>
  </a></li>

  <!-- Nav Item - Students -->
  <li class="nav-item"><a class="nav-link"
    href="${pageContext.request.contextPath}/admin/students/panel.jsp">
      <i class="fas fa-fw fa-user"></i> <span>Students</span>
  </a></li>

  <!-- Divider -->
  <hr class="sidebar-divider">

  <!-- Heading -->
  <div class="sidebar-heading">Content</div>

  <!-- Nav Item - Courses -->
  <li class="nav-item"><a class="nav-link"
    href="${pageContext.request.contextPath}/admin/courses/panel.jsp">
      <i class="fas fa-fw fa-book"></i> <span>Courses</span>
  </a></li>

</ul>
<!-- End of Sidebar -->
</html>
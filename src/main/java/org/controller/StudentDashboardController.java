package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import org.model.StudentModel;
import org.service.ExamService;
import org.service.StudentService;

@WebServlet("/studentDashboard")
public class StudentDashboardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("studentId") == null) {
            response.sendRedirect("StudLogin.html");
            return;
        }

        int studentId = (Integer) session.getAttribute("studentId");
        String studentName = (String) session.getAttribute("studentName");

        ExamService service = new ExamService();
        int total = service.getTotalExams(studentId);
        int completed = service.getCompletedExams(studentId);
        int pending = service.getPendingExams(studentId);

        StudentService studentService = new StudentService();
        StudentModel student = studentService.getStudentById(studentId);
        String email = (student != null && student.getEmail() != null) ? student.getEmail() : "student@exampro.com";
        String course = (student != null && student.getCourse() != null) ? student.getCourse() : "General";
        String mobile = (student != null && student.getMobile() != null) ? student.getMobile() : "-";
        String initial = (studentName != null && !studentName.isEmpty()) ? studentName.substring(0, 1).toUpperCase() : "S";

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'><head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Student Portal | ExamPro</title>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700;800&display=swap' rel='stylesheet'>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css' rel='stylesheet'>");

        out.println("<style>");
        out.println(":root{--nav-bg:#0f172a;--primary:#2563eb;--primary-dark:#1d4ed8;--bg-body:#f1f5f9;--text-main:#0f172a;}");
        out.println("*{box-sizing:border-box;font-family:'Plus Jakarta Sans',sans-serif;}");
        out.println("body{background:var(--bg-body);color:var(--text-main);margin:0;overflow-x:hidden;}");
        out.println("/* TOP NAVBAR (Horizontal on Desktop, Hamburger ☰ in Top-Right on Mobile) */");
        out.println(".dashboard-navbar{background:var(--nav-bg);color:#fff;position:sticky;top:0;z-index:1050;box-shadow:0 6px 24px rgba(15,23,42,0.18);border-bottom:1px solid rgba(255,255,255,0.08);}");
        out.println(".nav-container{max-width:1360px;margin:0 auto;padding:12px 24px;display:flex;align-items:center;justify-content:space-between;gap:16px;position:relative;}");
        out.println(".brand-box{display:flex;align-items:center;gap:12px;text-decoration:none;cursor:pointer;}");
        out.println(".brand-icon{width:40px;height:40px;border-radius:11px;background:linear-gradient(135deg,#2563eb,#1d4ed8);display:flex;align-items:center;justify-content:center;color:#fff;font-size:19px;box-shadow:0 4px 14px rgba(37,99,235,0.4);}");
        out.println(".brand-title{color:#fff;font-weight:800;margin:0;font-size:18.5px;line-height:1.1;}");
        out.println(".brand-title span{color:#60a5fa;}");
        out.println(".brand-sub{display:block;font-size:11px;color:#94a3b8;font-weight:500;}");
        out.println(".nav-menu{display:flex;align-items:center;gap:8px;list-style:none;margin:0;padding:0;}");
        out.println(".nav-item-link{display:inline-flex;align-items:center;gap:8px;padding:10px 16px;border-radius:10px;color:#cbd5e1;text-decoration:none;font-size:14px;font-weight:600;transition:all 0.2s ease;cursor:pointer;white-space:nowrap;}");
        out.println(".nav-item-link i{font-size:16px;color:#94a3b8;transition:color 0.2s ease;}");
        out.println(".nav-item-link:hover{background:rgba(255,255,255,0.08);color:#fff;}");
        out.println(".nav-item-link:hover i{color:#60a5fa;}");
        out.println(".nav-item-link.active{background:linear-gradient(135deg,#2563eb,#1d4ed8);color:#fff;box-shadow:0 4px 14px rgba(37,99,235,0.35);}");
        out.println(".nav-item-link.active i{color:#fff;}");
        out.println(".logout-link{display:inline-flex;align-items:center;gap:8px;padding:10px 16px;border-radius:10px;background:rgba(239,68,68,0.16);color:#f87171;text-decoration:none;font-weight:600;font-size:14px;transition:0.2s;white-space:nowrap;margin-left:4px;}");
        out.println(".logout-link:hover{background:#ef4444;color:#fff;}");
        out.println(".hamburger-btn{display:none;background:rgba(255,255,255,0.08);border:1px solid rgba(255,255,255,0.16);color:#fff;width:42px;height:42px;border-radius:10px;font-size:22px;align-items:center;justify-content:center;cursor:pointer;transition:0.2s;line-height:1;}");
        out.println(".hamburger-btn:hover{background:rgba(255,255,255,0.15);}");
        out.println("/* MAIN CONTENT */");
        out.println(".main{max-width:1360px;margin:0 auto;min-height:calc(100vh - 68px);padding:22px 24px;display:flex;flex-direction:column;}");
        out.println(".topbar{background:#fff;padding:14px 22px;border-radius:16px;display:flex;justify-content:space-between;align-items:center;gap:12px;margin-bottom:22px;border:1px solid rgba(226,232,240,0.8);box-shadow:0 4px 20px rgba(15,23,42,0.03);}");
        out.println(".breadcrumb-tag{font-size:11.5px;font-weight:700;color:#2563eb;text-transform:uppercase;letter-spacing:0.6px;}");
        out.println(".page-title{margin:2px 0 0 0;font-weight:800;font-size:20px;color:#0f172a;}");
        out.println(".profile-chip{display:flex;align-items:center;gap:10px;padding:6px 14px 6px 6px;border-radius:12px;background:#f8fafc;border:1px solid #e2e8f0;cursor:pointer;transition:0.2s;flex-shrink:0;}");
        out.println(".profile-chip:hover{border-color:#cbd5e1;background:#f1f5f9;}");
        out.println(".avatar{width:36px;height:36px;border-radius:10px;background:linear-gradient(135deg,#2563eb,#1d4ed8);color:#fff;font-weight:700;display:flex;align-items:center;justify-content:center;font-size:15px;}");
        out.println(".hero-card{background:linear-gradient(135deg,#0f172a 0%,#1e3a8a 55%,#2563eb 100%);border-radius:20px;padding:30px 34px;color:#fff;display:flex;justify-content:space-between;align-items:center;gap:20px;margin-bottom:24px;box-shadow:0 14px 32px rgba(30,58,138,0.22);}");
        out.println(".hero-card h2{font-weight:800;font-size:25px;margin-bottom:6px;}");
        out.println(".hero-card p{color:#dbeafe;margin:0;font-size:14.5px;max-width:540px;}");
        out.println(".stat-card{background:#fff;border-radius:16px;padding:22px;border:1px solid rgba(226,232,240,0.8);box-shadow:0 4px 16px rgba(15,23,42,0.03);display:flex;align-items:center;gap:18px;transition:transform 0.2s;}");
        out.println(".stat-card:hover{transform:translateY(-3px);}");
        out.println(".stat-icon{width:56px;height:56px;border-radius:14px;display:flex;align-items:center;justify-content:center;font-size:24px;flex-shrink:0;}");
        out.println(".stat-value{font-size:28px;font-weight:800;margin:0;line-height:1.1;color:#0f172a;}");
        out.println(".stat-label{font-size:13px;font-weight:600;color:#64748b;margin-bottom:4px;}");
        out.println(".action-card{background:#fff;border-radius:16px;padding:24px;border:1px solid rgba(226,232,240,0.8);box-shadow:0 4px 16px rgba(15,23,42,0.03);height:100%;display:flex;flex-direction:column;justify-content:space-between;transition:0.22s;}");
        out.println(".action-card:hover{transform:translateY(-4px);box-shadow:0 12px 26px rgba(15,23,42,0.08);}");
        out.println(".workspace-box{flex:1;display:flex;flex-direction:column;background:#fff;border-radius:18px;border:1px solid rgba(226,232,240,0.8);box-shadow:0 6px 24px rgba(15,23,42,0.04);overflow:hidden;min-height:calc(100vh - 175px);}");
        out.println("#studentFrame{width:100%;flex:1;min-height:calc(100vh - 180px);border:none;background:#f8fafc;}");
        out.println(".profile-card{max-width:680px;margin:10px auto;background:#fff;border-radius:20px;border:1px solid #e2e8f0;box-shadow:0 10px 30px rgba(15,23,42,0.05);overflow:hidden;}");
        out.println(".profile-banner{height:110px;background:linear-gradient(135deg,#1e3a8a,#2563eb);}");
        out.println(".profile-avatar-lg{width:84px;height:84px;border-radius:22px;background:#0f172a;color:#fff;font-size:34px;font-weight:800;display:flex;align-items:center;justify-content:center;border:4px solid #fff;margin-top:-42px;box-shadow:0 6px 16px rgba(0,0,0,0.12);}");
        out.println(".info-item{background:#f8fafc;border:1px solid #e2e8f0;border-radius:12px;padding:14px 18px;}");
        out.println(".info-label{font-size:12px;font-weight:600;color:#64748b;text-transform:uppercase;}");
        out.println(".info-val{font-size:15px;font-weight:700;color:#0f172a;margin-top:2px;word-break:break-word;}");
        out.println("/* MOBILE RESPONSIVE MEDIA QUERY */");
        out.println("@media(max-width:992px){");
        out.println("  .hamburger-btn{display:inline-flex;}");
        out.println("  .nav-menu{display:flex;flex-direction:column;align-items:stretch;gap:6px;position:absolute;top:100%;left:0;right:0;background:#0f172a;padding:0 20px;max-height:0;overflow:hidden;opacity:0;transition:max-height 0.32s ease, opacity 0.25s ease, padding 0.25s ease;border-bottom:1px solid rgba(255,255,255,0.08);box-shadow:0 16px 32px rgba(15,23,42,0.35);}");
        out.println("  .nav-menu.open{max-height:420px;opacity:1;padding:14px 20px 18px 20px;}");
        out.println("  .nav-item-link,.logout-link{width:100%;margin-left:0;padding:12px 16px;}");
        out.println("  .logout-link{justify-content:center;margin-top:4px;}");
        out.println("  .main{padding:16px;}");
        out.println("  .hero-card{flex-direction:column;align-items:flex-start;padding:22px 20px;}");
        out.println("  .hero-card h2{font-size:21px;}");
        out.println("}");
        out.println("@media(max-width:576px){");
        out.println("  .nav-container{padding:10px 16px;}");
        out.println("  .main{padding:10px;}");
        out.println("  .topbar{padding:10px 14px;border-radius:12px;margin-bottom:14px;}");
        out.println("  .profile-chip{padding:5px;}");
        out.println("  .page-title{font-size:16.5px;}");
        out.println("  .breadcrumb-tag{font-size:10px;}");
        out.println("  .hero-card{padding:18px 16px;border-radius:16px;}");
        out.println("  .hero-card h2{font-size:19px;}");
        out.println("  .hero-card p{font-size:13.5px;}");
        out.println("  .hero-action-wrap,.hero-action-wrap button{width:100%;}");
        out.println("  .stat-card,.action-card{padding:16px;border-radius:14px;}");
        out.println("  .workspace-box{min-height:calc(100vh - 140px);border-radius:14px;}");
        out.println("  #studentFrame{min-height:calc(100vh - 145px);}");
        out.println("}");
        out.println("</style>");
        out.println("</head><body>");

        // TOP RESPONSIVE NAVBAR WITH HAMBURGER (☰) IN TOP RIGHT
        out.println("<nav class='dashboard-navbar'>");
        out.println("  <div class='nav-container'>");
        out.println("    <div class='brand-box' onclick='showSection(\"overview\")'>");
        out.println("      <div class='brand-icon'><i class='bi bi-mortarboard-fill'></i></div>");
        out.println("      <div><h5 class='brand-title'>Exam<span>Pro</span></h5><span class='brand-sub'>Student Portal</span></div>");
        out.println("    </div>");
        out.println("    <button class='hamburger-btn' id='hamburgerBtn' onclick='toggleMenu()' aria-label='Toggle Navigation'>&#9776;</button>");
        out.println("    <div class='nav-menu' id='navMenu'>");
        out.println("      <a class='nav-item-link active' id='menu-overview' onclick='showSection(\"overview\")'><i class='bi bi-grid-1x2-fill'></i><span>Dashboard</span></a>");
        out.println("      <a class='nav-item-link' id='menu-exams' onclick='openFrame(this, \"studentExamList\", \"Available Exams\", \"Student Portal / Active Exams\")'><i class='bi bi-journal-text'></i><span>Active Exams</span></a>");
        out.println("      <a class='nav-item-link' id='menu-results' onclick='openFrame(this, \"studentResults\", \"My Exam Results\", \"Student Portal / Results\")'><i class='bi bi-bar-chart-fill'></i><span>Results</span></a>");
        out.println("      <a class='nav-item-link' id='menu-profile' onclick='showSection(\"profile\")'><i class='bi bi-person-badge-fill'></i><span>Profile</span></a>");
        out.println("      <a href='logout' class='logout-link' onclick='closeMenu()'><i class='bi bi-box-arrow-right'></i><span>Logout</span></a>");
        out.println("    </div>");
        out.println("  </div>");
        out.println("</nav>");

        // MAIN
        out.println("<div class='main'>");

        // TOPBAR
        out.println("  <header class='topbar'>");
        out.println("    <div>");
        out.println("      <span class='breadcrumb-tag' id='topBreadcrumb'>Student Portal / Overview</span>");
        out.println("      <h4 class='page-title' id='topTitle'>Welcome, " + studentName + "!</h4>");
        out.println("    </div>");
        out.println("    <div class='profile-chip' onclick='showSection(\"profile\")'>");
        out.println("      <div class='avatar'>" + initial + "</div>");
        out.println("      <div class='d-none d-sm-block'>");
        out.println("        <div style='font-size:13px;font-weight:700;color:#0f172a;line-height:1.2;'>" + studentName + "</div>");
        out.println("        <div style='font-size:11px;color:#64748b;'>ID: #" + studentId + " • " + course + "</div>");
        out.println("      </div>");
        out.println("    </div>");
        out.println("  </header>");

        // 1) OVERVIEW SECTION
        out.println("  <div id='sectionOverview'>");
        out.println("    <div class='hero-card'>");
        out.println("      <div>");
        out.println("        <span style='background:rgba(255,255,255,0.16);padding:4px 12px;border-radius:999px;font-size:12px;font-weight:600;display:inline-block;margin-bottom:10px;'><i class='bi bi-stars me-1'></i> Online Assessment Portal</span>");
        out.println("        <h2>Ready for your next assessment, " + studentName + "?</h2>");
        out.println("        <p>Track your assigned examinations, attempt pending tests in real time, and review your detailed scorecards without leaving this dashboard.</p>");
        out.println("      </div>");
        out.println("      <div class='hero-action-wrap'>");
        out.println("        <button onclick='openFrame(document.getElementById(\"menu-exams\"), \"studentExamList\", \"Available Exams\", \"Student Portal / Active Exams\")' class='btn btn-light fw-bold px-4 py-2 rounded-3 shadow-sm' style='color:#1e3a8a;white-space:nowrap;'><i class='bi bi-play-circle-fill me-1'></i> Take an Exam</button>");
        out.println("      </div>");
        out.println("    </div>");

        // STATS
        out.println("    <div class='row g-3 g-md-4 mb-4'>");
        out.println("      <div class='col-lg-4 col-sm-6'>");
        out.println("        <div class='stat-card'>");
        out.println("          <div class='stat-icon' style='background:#eff6ff;color:#2563eb;'><i class='bi bi-journal-bookmark-fill'></i></div>");
        out.println("          <div><div class='stat-label'>Total Assigned Exams</div><h3 class='stat-value'>" + total + "</h3></div>");
        out.println("        </div>");
        out.println("      </div>");
        out.println("      <div class='col-lg-4 col-sm-6'>");
        out.println("        <div class='stat-card'>");
        out.println("          <div class='stat-icon' style='background:#ecfdf5;color:#059669;'><i class='bi bi-check2-circle'></i></div>");
        out.println("          <div><div class='stat-label'>Completed Exams</div><h3 class='stat-value'>" + completed + "</h3></div>");
        out.println("        </div>");
        out.println("      </div>");
        out.println("      <div class='col-lg-4 col-sm-12'>");
        out.println("        <div class='stat-card'>");
        out.println("          <div class='stat-icon' style='background:#fffbeb;color:#d97706;'><i class='bi bi-hourglass-split'></i></div>");
        out.println("          <div><div class='stat-label'>Pending Exams</div><h3 class='stat-value'>" + pending + "</h3></div>");
        out.println("        </div>");
        out.println("      </div>");
        out.println("    </div>");

        // QUICK ACTIONS
        out.println("    <div class='row g-4'>");
        out.println("      <div class='col-md-6'>");
        out.println("        <div class='action-card'>");
        out.println("          <div>");
        out.println("            <div class='d-flex align-items-center gap-2 mb-2'><i class='bi bi-pencil-square fs-4 text-primary'></i><h5 class='fw-bold m-0'>Start / View Examinations</h5></div>");
        out.println("            <p class='text-muted small mb-3'>Browse all available exams for your course and start pending assessments immediately in this workspace.</p>");
        out.println("          </div>");
        out.println("          <div><button onclick='openFrame(document.getElementById(\"menu-exams\"), \"studentExamList\", \"Available Exams\", \"Student Portal / Active Exams\")' class='btn btn-primary btn-sm px-3 fw-semibold rounded-2'>Open Exam List <i class='bi bi-arrow-right ms-1'></i></button></div>");
        out.println("        </div>");
        out.println("      </div>");
        out.println("      <div class='col-md-6'>");
        out.println("        <div class='action-card'>");
        out.println("          <div>");
        out.println("            <div class='d-flex align-items-center gap-2 mb-2'><i class='bi bi-graph-up-arrow fs-4 text-primary'></i><h5 class='fw-bold m-0'>Performance & Results</h5></div>");
        out.println("            <p class='text-muted small mb-3'>Check your subject-wise marks, accuracy percentages, and pass/fail status across completed exams.</p>");
        out.println("          </div>");
        out.println("          <div><button onclick='openFrame(document.getElementById(\"menu-results\"), \"studentResults\", \"My Exam Results\", \"Student Portal / Results\")' class='btn btn-outline-primary btn-sm px-3 fw-semibold rounded-2'>View My Results <i class='bi bi-arrow-right ms-1'></i></button></div>");
        out.println("        </div>");
        out.println("      </div>");
        out.println("    </div>");
        out.println("  </div>");

        // 2) PROFILE SECTION
        out.println("  <div id='sectionProfile' class='d-none'>");
        out.println("    <div class='profile-card'>");
        out.println("      <div class='profile-banner'></div>");
        out.println("      <div class='px-4 pb-4'>");
        out.println("        <div class='d-flex justify-content-between align-items-end mb-4'>");
        out.println("          <div class='profile-avatar-lg'>" + initial + "</div>");
        out.println("          <span class='badge bg-primary-subtle text-primary border border-primary-subtle px-3 py-2 rounded-pill fw-semibold'><i class='bi bi-patch-check-fill me-1'></i> Active Student</span>");
        out.println("        </div>");
        out.println("        <h4 class='fw-bold mb-1'>" + studentName + "</h4>");
        out.println("        <p class='text-muted small mb-4'>Registered Student Account • ExamPro Online Examination System</p>");
        out.println("        <div class='row g-3'>");
        out.println("          <div class='col-sm-6'><div class='info-item'><div class='info-label'><i class='bi bi-hash me-1'></i>Student ID</div><div class='info-val'>#" + studentId + "</div></div></div>");
        out.println("          <div class='col-sm-6'><div class='info-item'><div class='info-label'><i class='bi bi-mortarboard me-1'></i>Enrolled Course</div><div class='info-val'>" + course + "</div></div></div>");
        out.println("          <div class='col-sm-6'><div class='info-item'><div class='info-label'><i class='bi bi-envelope me-1'></i>Email Address</div><div class='info-val'>" + email + "</div></div></div>");
        out.println("          <div class='col-sm-6'><div class='info-item'><div class='info-label'><i class='bi bi-telephone me-1'></i>Mobile Number</div><div class='info-val'>" + mobile + "</div></div></div>");
        out.println("        </div>");
        out.println("      </div>");
        out.println("    </div>");
        out.println("  </div>");

        // 3) IFRAME WORKSPACE SECTION
        out.println("  <div id='sectionWorkspace' class='workspace-box d-none'>");
        out.println("    <iframe name='studentFrame' id='studentFrame' src='about:blank'></iframe>");
        out.println("  </div>");

        out.println("</div>"); // end main

        out.println("<script>");
        out.println("function toggleMenu(){");
        out.println("  const menu = document.getElementById('navMenu');");
        out.println("  const btn = document.getElementById('hamburgerBtn');");
        out.println("  menu.classList.toggle('open');");
        out.println("  btn.innerHTML = menu.classList.contains('open') ? '&times;' : '&#9776;';");
        out.println("}");
        out.println("function closeMenu(){");
        out.println("  const menu = document.getElementById('navMenu');");
        out.println("  const btn = document.getElementById('hamburgerBtn');");
        out.println("  menu.classList.remove('open');");
        out.println("  if(btn) btn.innerHTML = '&#9776;';");
        out.println("}");
        out.println("function clearNav(){document.querySelectorAll('.nav-menu .nav-item-link').forEach(a=>a.classList.remove('active'));}");
        out.println("function openFrame(el, url, title, crumb){");
        out.println("  clearNav();");
        out.println("  if(el) el.classList.add('active');");
        out.println("  document.getElementById('topTitle').textContent = title;");
        out.println("  document.getElementById('topBreadcrumb').textContent = crumb;");
        out.println("  document.getElementById('sectionOverview').classList.add('d-none');");
        out.println("  document.getElementById('sectionProfile').classList.add('d-none');");
        out.println("  document.getElementById('sectionWorkspace').classList.remove('d-none');");
        out.println("  document.getElementById('studentFrame').src = url;");
        out.println("  closeMenu();");
        out.println("}");
        out.println("function showSection(sec){");
        out.println("  clearNav();");
        out.println("  document.getElementById('sectionOverview').classList.add('d-none');");
        out.println("  document.getElementById('sectionProfile').classList.add('d-none');");
        out.println("  document.getElementById('sectionWorkspace').classList.add('d-none');");
        out.println("  if(sec==='profile'){");
        out.println("    document.getElementById('menu-profile').classList.add('active');");
        out.println("    document.getElementById('topTitle').textContent = 'My Student Profile';");
        out.println("    document.getElementById('topBreadcrumb').textContent = 'Student Portal / Profile';");
        out.println("    document.getElementById('sectionProfile').classList.remove('d-none');");
        out.println("  } else {");
        out.println("    document.getElementById('menu-overview').classList.add('active');");
        out.println("    document.getElementById('topTitle').textContent = 'Welcome, " + studentName.replace("'", "\\'") + "!';");
        out.println("    document.getElementById('topBreadcrumb').textContent = 'Student Portal / Overview';");
        out.println("    document.getElementById('sectionOverview').classList.remove('d-none');");
        out.println("  }");
        out.println("  closeMenu();");
        out.println("}");
        out.println("</script>");

        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

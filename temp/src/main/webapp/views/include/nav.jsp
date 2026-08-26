<%@page import="com.office.toypjt.ToyPjtConfig"%>
<%@page import="com.office.toypjt.member.MemberConfig"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link href="./css/index.css" rel="stylesheet" type="text/css">

<nav>

    <div class="nav_wrap">

        <a href="<%= request.getContextPath().concat(ToyPjtConfig.HOME) %>">
            HOME
        </a>
		&nbsp;&nbsp; | &nbsp;&nbsp;
		
        <%
            String signinedMemId = null;
            if (session.getAttribute("signinedMemId") != null) {
                signinedMemId = String.valueOf(session.getAttribute("signinedMemId"));
        %>
                <a href="<%= request.getContextPath().concat(MemberConfig.MEMBER_MODIFY_FORM) %>">
                    MODIFY
                </a>
                &nbsp;&nbsp; | &nbsp;&nbsp;
                <a href="<%= request.getContextPath().concat(MemberConfig.MEMBER_DELETE) %>">
                    DELETE
                </a>
                &nbsp;&nbsp; | &nbsp;&nbsp;
                <a href="<%= request.getContextPath().concat(MemberConfig.MEMBER_DELETE) %>">
                    DELETE
                </a>
                &nbsp;&nbsp; | &nbsp;&nbsp;
                <a href="<%= request.getContextPath().concat(MemberConfig.MEMBER_DELETE) %>">
                    DELETE
                </a>
        <%
            } else {
        %>
                <a href="<%= request.getContextPath().concat(MemberConfig.MEMBER_SIGNUP_FORM) %>">
                    SIGN-UP
                </a>
                &nbsp;&nbsp; | &nbsp;&nbsp;
                <a href="<%= request.getContextPath().concat(MemberConfig.MEMBER_SIGNIN_FORM) %>">
                    SIGN-IN
                </a>
        <%
            }
        %>

    </div>

</nav>
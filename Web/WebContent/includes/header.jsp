<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="es">

<head>
  <meta charset="utf-8">
  <title>Super Sarasa IAPP</title>
  <meta name="description" content="Supermercado Sarasa de IAPP Pinamar 1er Cuatrimestre">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="manifest" href="/Web/site.webmanifest">
  <link rel="apple-touch-icon" href="icon.png">
  <!-- Place favicon.ico in the root directory -->

  <link rel="stylesheet" href="/Web/css/normalize.css">
  <link rel="stylesheet" href="/Web/css/vendor/bootstrap.min.css">
  <link rel="stylesheet" href="/Web/css/vendor/fontawesome/css/all.css">
  <link rel="stylesheet" href="/Web/css/main.css">

  <meta name="theme-color" content="#fafafa">
</head>

<body>
  <!--[if IE]>
    <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
  <![endif]-->

	<nav class="navbar navbar-expand-md navbar-light fixed-top bg-light">
	  <a class="navbar-brand" href="/Web/index.jsp"><img alt="Super Sarasa IAPP logo" src="/Web/img/logo_transparent_nowords.png" height="40" width="70"/><span class="pl-1">SARASA IAPP</span></a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navegacion" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <% boolean logged_in = true; 
	  if (logged_in){%>
	  <div class="collapse navbar-collapse" id="navegacion">
	    <ul class="navbar-nav mr-auto">
	    	<li class="nav-item">
	    		<a class="btn btn-outline-success" href="/Web/facturacion/vender.jsp">Vender</a>
	    	</li>
	    	<li class="nav-item">
	    		<a class="nav-link" href="/Web/empleados/index.jsp">Empleados</a>
	    	</li>
	    	<li class="nav-item">
	    		<a class="nav-link" href="/Web/productos/index.jsp">Productos</a>
	    	</li>
	    	<li class="nav-item">
	    		<a class="nav-link" href="/Web/facturacion/index.jsp">Facturación</a>
	    	</li>
		</ul>
	    <a class="btn btn-outline-info" href="/Web/Private?action=logout">Cerrar sesión</a>
	  </div>
	  <%} %>
	</nav>
	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="index.jsp">Inicio</a></li>
	    <li class="breadcrumb-item active" aria-current="page">Nivel 2</li>
	  </ol>
	</nav>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="es">

<head>
  <meta charset="utf-8">
  <title>Super Sarasa IAPP</title>
  <meta name="description" content="Supermercado Sarasa de IAPP Pinamar 1er Cuatrimestre">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="manifest" href="site.webmanifest">
  <link rel="apple-touch-icon" href="icon.png">
  <!-- Place favicon.ico in the root directory -->

  <link rel="stylesheet" href="css/normalize.css">
  <link rel="stylesheet" href="css/vendor/bootstrap.min.css">
  <link rel="stylesheet" href="css/vendor/fontawesome/css/all.css">
  <link rel="stylesheet" href="css/main.css">

  <meta name="theme-color" content="#fafafa">
</head>

<body>
  <!--[if IE]>
    <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
  <![endif]-->

	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	  <a class="navbar-brand" href="index.jsp">Super Sarasa IAPP</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navegacion" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navegacion">
	    <ul class="navbar-nav mr-auto">
	    	<li class="nav-item">
	    		<a class="nav-link" href="empleados.jsp">Empleados</a>
	    	</li>
	    	<li class="nav-item">
	    		<a class="nav-link" href="productos.jsp">Productos</a>
	    	</li>
		    <li class="nav-item dropdown">
			    <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">Facturación</a>
			    <div class="dropdown-menu" aria-labelledby="dropdown01">
					<a class="dropdown-item" href="#">Listar Facturas</a>
					<a class="dropdown-item" href="#">Ingresar Cobranza</a>
				</div>
			</li>
		</ul>
	    <a class="btn btn-primary" href="login.jsp">Iniciar sesión</a>
	  </div>
	</nav>
	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="index.jsp">Inicio</a></li>
	    <li class="breadcrumb-item"><a href="#">Nivel 1</a></li>
	    <li class="breadcrumb-item active" aria-current="page">Nivel 2</li>
	  </ol>
	</nav>
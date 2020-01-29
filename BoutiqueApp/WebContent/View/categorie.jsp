<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
        html, body {
            height: 100%;
            width: 100%;
            margin: 0;
        }

        #wrapper {
            min-height: 100%; 
        }
</style>
<title>Categorie Boutique</title>
</head>
<body>
	<body id="wrapper" style="background-color: black;">
	<div>
		<div style="margin-top: 15px; margin-bottom: 25px;border-radius: 5px; border-style: groove; border-width: 5px; border-color: orange;">
			<h1 style="color: orange; font-family: cursive;font-size: xx-large;text-align: center; font-style: italic;font-weight: bolder;">Ma Boutique</h1>
		</div>
		<div>
			<table>
				<tr>
					<td style="text-align:center; border-radius: 5px; border-style: groove; border-width: 5px;border-color: orange; padding: 5px;">
							<span style="font-weight: bolder; color: orange;">Menu</span><br/><br/>
							<a href="AcceuilServlet" style="color: orange;">Accueil</a><br/>
							<a href="Categorie" style="color: orange;">Catégories</a><br/>
							<a href="Panier" style="color: orange;">Panier</a><br/>
					</td>
					<td style="border-radius: 5px; border-style: groove; border-width: 5px; height: 100%; width: 100%; border-color: orange;">
						<!--  <div style="color: orange; " id="central_page">
							<div style="text-align: center;">	-->	
							<table>
								<tr>
									<td style="color: orange; text-align: center;">
										<span style="font-weight: bolder; color: orange;">Categorie</span><br/><br/>
										<c:forEach var="categorie" items="${categoriesList}">
											<a href="Produit?id=${categorie.id}" style="color: orange;">${categorie.nom}</a><br/>
										</c:forEach>
									</td>
								</tr>
							</table>
						<!--  </div> 	
							</div>
						-->
					</td>
				</tr>
			</table>
		</div>
	</div>
	
</body>
</html>
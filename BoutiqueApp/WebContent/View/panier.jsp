<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
<title>Accueil</title>
<script src="https://code.jquery.com/jquery-1.10.2.js"
	type="text/javascript"></script>
</head>
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
					<td style="border-radius: 5px; border-style: groove; border-width: 5px; height: 100%; width: 100%; border-color: orange; padding: 25px; align-content: center;">
						<div style="color: orange; text-align: center;" id="central_page">
							<span style="font-size: large;">Panier</span><br/><br/>
							<table style="margin: 0 auto;">
										
								<c:forEach var="elem" items="${panier_afficher}">
									<tr>
										<td>${elem.nom}</td>
										<td style="padding-left: 25px;">${elem.quantit}</td>
										<td style="padding-left: 25px;"><a href="SupprimerPanier?produit_id=${elem.id}" style="color: orange;">Supprimer produit</a></td>
									</tr> 
								</c:forEach>
							</table>
							<c:if test="${not empty panier_afficher}">
								<div><a href="ValiderPanier" style="color: orange;">Valider la commande</a></div>
							</c:if>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
</body>
</html>
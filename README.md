#Fichier README.md

    ** - Projet AssuerPlus - **
    Projet développé dans le cadre de de ma formation en Développement d'application Java.

    But du projet :
        Développer un POC d’une application pour une compagnie d'assurance.
        L’application doit permettre aux assurés de déclarer un accident en quelques clics, et de charger des photos et fichiers.
        Le projet doit suivre la méthodologie Agile SCRUM.
        L'application doit être responsive, afin d'être utilisable sur un smartphone.

    Etat du Projet :
        POC - Fonctionnel, reste certaines fonctionnalités "bonus" à développer si le POC est validé par le client

    Reste à faire : 
        Pour le POC : 
            - Déployer l'application

        Après Valide : 
            - Mettre en place un système de Log
            - Faire la documentation
            - Sprint 3 : US5 - Fonctionnalités liée à la recherche de garages

    ** - Utilisation de l'application - **
        L'utilisateur commence sur une page de connexion, il est invité à saisir ses logins. Cette connexion est valable 5min (durée mise en place pour permettre de tester la validité), suite à quoi, l'utilisateur sera redirigé vers la page de connexion.
        Après quoi, il est redirigé vers le "Menu principal". Depuis celui-ci il peut :
            - Déclarer un nouvel accident : Bouton "Nouvelle déclaration"
            - Consulter les déclarations déjà effectuées : bouton "Mes déclarations"

    ** Nouvelle déclaration ** 
        - Sur cette page l'utilisateur est invité à saisir les données nécessaires pour réaliser une déclaration de sinistre, si une ou plusieurs données sont manquantes, l'utilisateur est averti et invité à compléter le formulaire.
        - Une fois les données saisis, il peut valider le formulaire. Il est alors redirigé vers une page de validation, qui l'averti que sa demande à bien été prise en compte (C'est ici que sera implémenté les fonctionnalités de recherche de garage)
        En parallèle, l'application enregistre la déclaration de l'assuré en BDD, et upload les fichiers sur le serveur, ces derniers sont préalablement vérifiés et renommés.
    
    ** Mes déclaration **
        - Sur cette page est affiché la liste des sinistres enregistrés en BDD pour l'utilisateur connecté.
        - Si l'utilisateur n'a pas de sinistre déclaré, un message le lui notifie.

    Une barre de navigation permet à l'utilisateur de naviguer entre les différentes pages de l'application.

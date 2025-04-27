# Vaadin harjoitustyö
Valitettavasti ajanpuutteen vuoksi tehtävän suoritus jäi hieman vajaaksi, mutta uskoakseni jokaisesta osa-alueesta pitäisi saada vähintään 1p.

Tehtävänannon mukaisesti tarkoitus oli rakentaa sovellus jolla voi hallinnoida tulevia ja menneitä tapahtumia, sekä liittää tapahtumiin artisteja ja paikkoja

- Luotu SPA sovellus Vaadin generaattorilla
- Pääentiteetti 'Event' - luokka joka kuvastaa tapahtumaa. Tapahtumalla on nimi ja tapahtuma-aika
- Toinen entiteetti valmisteltu 'Artist' jossa olisi M:M suhde eventiin - sama artisti voi olla useassa eventissä ja yksi event sisältää monta artistia
- Tarkoitus oli tehdä vielä 1:1 ja 1:M entiteetit mutta ne jäivät.. Suunnitelmissa oli esimerkikis 'tapahtumapaikka' -entiteetti 1:M suhteella eventiin
- Sovellus aukeaa päänäkymään jossa eventit listattuna gridiin. Muutama esivalmisteltu event oletuksena. Add- ja delete painikkeista voi lisätä ja poistaa tapahtumia, hakukentällä filtteröidä nimen mukaan
- Styles.css tiedostossa globaalisti muutettu fontti, _Roboto_
- Security palikka otettu käyttöön. Kaikille avoin päänäkymä, vasemmassa alanurkassa on 'sign in' painike. Sitä painamalla pääsee kirjautumaan. Tunnuksilla admin/admin tulee toinen näkymä esille - tämä jäi Vaadinin default henkilölistaksi, tarkoitus oli muokata artistien ja tapahtumapaikkojen muokkausnäkymiksi kirjautuneelle käyttäjälle
- Julkaistu Githubiin ja salasanat tallennetaan hashattuna

- Tehtävään liittyvä uuden frameworkin käyttöönotto tuntui kohtalaisen vaativalle, tosin tähän syynä se että suoritin monimuoto-opiskelijana kurssia täysin video-opetuksen pohjalta enkä osallistunut aktiivisiin koodaussessioihin. Siitä kuitenkin erityismaininta, että videot oli muutoin erityisen hyviä ja 'liveopetusta' on mukava kuunnella näinä aikoina kun iso osa kursseista on ennalta nauhoitettuja sessioita. Savonian aiemmat kurssit joilla Javaa on käytetty antoivat hyvät lähtökohdat kurssin ja harjoitustehtävän suorittamiseksi. Etäopetus- ja aikahaasteista huolimatta koen että Vaadin vaikuttaa varsin käyttökelpoiselta ja yksinkertaiseltakin frameworkilta hyvän näköisten nettisivujen toteuttamiseksi.

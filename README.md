
<p>Powyższy projekt został napisany w frameworku Play w wersji 2.2.1 oraz przy użyciu MySQL 5.5.29.
</p>

<p>Aby uruchomić aplikację na swojej maszynie należy najpierw sklonować projekt, czyli będąc w linii poleceń wykonać:
  <br>
  <code>git clone https://github.com/alexandder/simpleCrm.git</code>
  <br>
  Następnie należy przygotować bazę danych. Aplikacja zakłada istnienie bazy danych o nazwie 'crmDatabase' i użytkownika mającego prawa do tej bazy o nazwie 'testuser' i haśle 'test'. Powyższe ustawienia można zmienić w pliku
  <br>
  <code>conf/application.conf</code>
  <br>
  Po przygotowaniu bazy danych należy wykonać w niej dwa skrypty znajdujące się w:
  <br>
  <code>conf/application/evolutions/default/1.sql</code>
  <br>
  <code>conf/application/evolutions/default/2.sql</code>
  <br>
  Następnie będąc w katalogu projektu należy wykonać 
  <br>
  <code>play run</code>
  <br>
  W następstwie tego aplikacja powinna być osiągalna poprzez wpisanie adresu localhost:9000 w przeglądarce. Do aplikacji można zalogować się używając dwóch testowych kont: 
  <br>
  <code>email: admin@example.com hasło:test</code>
  <br>
  <code>email: user@example.com hasło:test</code>
</p>

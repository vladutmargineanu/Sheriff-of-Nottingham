# Sheriff-of-Nottingham
Homework for the Object Oriented Programming course @ ACS, UPB 2018

# Algorithm

Pentru simularea jocului am folosit 3 clase fundamentale:
- clasa Player care incapsuleaza un jucator;
- clasa Card care incapsuleaza proprietatile unei carti de joc;
- clasa Game care simuleaza exact exact etapele jocului.

Clasa Player este extinsa de alte 3 clase care simuleaza cele 3 strategii de
joc, si anume: BasicPlayer, GreedyPlayer si BribedPlayer. Fiecare dintre aceste
clase are implementata propria strategie in metodele addAssetsInBag() si 
beSheriff(), care sunt metode abstracte din clasa Player create special pentru
a fi suprascrise.

Clasa Card pastreaza toate atributele necesare unei carti. Constructorul clasei
primeste un id si dupa acel id pune completeaza toate atributele specifice
cartii respective.

In clasa Game se intampla adevarata magie, in constructor se creeaza lista de
jucatori si coada de carti din care fiecare jucator urmeaza sa traga carti. In
metoda play() se simuleaza jocul propriu zis, cat timp nu au fost toti
jucatorii de doua ori in rolul de serif, toti trag carti pana au 6 carti in
mana. Apoi jucatorii care nu au rolul de seriff isi adauga produsele in saci
dupa strategiile proprii. Dupa adaugarea produselor in sac se simuleaza inspectia
serifului, iar in final comerciantii adauga produsele ce au trecut de inspectie
in lista finala de produse, lista care le va aduce puncte la terminarea jocului.
Dupa ce fiecare jucator a fost seriff cel putin de doua ori se calculeaza cate
puncte are fiecare jucator in total si ce bonusuri primeste, ca in final
acestia sa fie sortati in functie de scor.

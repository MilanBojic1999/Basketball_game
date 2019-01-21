#include <stdio.h>
#include <stdlib.h>
#include <string.h>
typedef struct T{
    char naziv[25];
    int napad;
    int odbrana;
    struct T *sledeci;
}Tim;

Tim* napravi_tim(char naziv[],int napad,int odbrana){
    Tim *novi=(Tim* ) malloc(sizeof(Tim));
    strcpy(novi->naziv,naziv);
    novi->napad=napad;
    novi->odbrana=odbrana;
    novi->sledeci=NULL;
    return novi;
}

void Info_meni(){
    printf("1) Ucitaj iz fajla\n");
    printf("2) Dodaj na fajl timove\n");
    printf("3) Prepisi preko fajla\n");
    printf("4) Odigraj mec\n");
    printf("0) Vidi rezultate");
    printf("\n");
}

Tim* napravi_ligu(Tim* stablo,Tim* novi){
    if(!stablo) return novi;
    Tim* trenutni=stablo;
    if(strcmp(stablo->naziv,novi->naziv)>0){
        novi->sledeci=stablo;
        return novi;
    }
    while(trenutni->sledeci && strcmp(trenutni->sledeci->naziv,novi->naziv)<0){
        trenutni=trenutni->sledeci;
    }
        novi->sledeci=trenutni->sledeci;
        trenutni->sledeci=novi;
    return stablo;
}

void info(char *ime,int *n,int *o){
    printf("Kako se zove vas tim? ");
    scanf(" %[^\n]",ime);
    printf("Kakav je napad tima? ");
    scanf("%d",n);
    printf("Kakva je odbrana time? ");
    scanf("%d",o);
    printf("\n");
}
Tim* otvori_fajl(){
    Tim* lista=NULL;
    char *token,naziv[25],linija[100];
    int napad,odbrana;
    FILE* f=fopen("Kosarka.txt","r");
    while(fgets(linija,100,f)==linija){
        token=strtok(linija,"-");
        strcpy(naziv,token);
        token=strtok(NULL,":");
        napad= atoi(token);
        token=strtok(NULL,":");
        odbrana=atoi(token);
        lista=napravi_ligu(lista,napravi_tim(naziv,napad,odbrana));
    }
    fclose(f);
    return lista;
}

void dodaj_na_fajl(Tim* stablo){
    FILE* f=fopen("Kosarka.txt","a");
    while(stablo){
        fprintf(f,"%s - %d:%d\n",stablo->naziv,stablo->napad,stablo->odbrana);
        stablo=stablo->sledeci;
    }
    fclose(f);
}

Tim* dodaj_u_listu(Tim* predstavnik){
    do{
        char ime[25];
        int napad,odbrana;
        info(ime,&napad,&odbrana);
        if(!napad || !odbrana) break;
        predstavnik=napravi_ligu(predstavnik,napravi_tim(ime,napad,odbrana));
    }while(1);
    return predstavnik;
}

void obrisi(Tim* tabela){
    Tim* zaBrisanje;
    while(tabela){
        zaBrisanje=tabela;
        tabela=tabela->sledeci;
        free(zaBrisanje);
    }
}

void ispisi(Tim* stablo){
    if(!stablo) return;
    int i;
    for(i=1;stablo;i++,stablo=stablo->sledeci){
        printf("%d) %s-%d:%d\n",i,stablo->naziv,stablo->napad,stablo->odbrana);
    }
}

int main()
{
    Tim* predstavnik=NULL;
    int n;
    do{
        Info_meni();
        scanf("%d",&n);
        switch(n){
            case 1:
                predstavnik=otvori_fajl();
                break;
            case 2:
                predstavnik=dodaj_u_listu(predstavnik);
                dodaj_na_fajl(predstavnik);
                break;

        }
    }while(n);
    ispisi(predstavnik);
    obrisi(predstavnik);
    return 0;
}

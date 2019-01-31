#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
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
    printf("3) Obrisi tim\n");
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

Tim* obrisi_tim(Tim* lista,int n){ //brise n-ti tim
    Tim* zaBrisanje,*tmp;
    if(n==1){
        zaBrisanje=lista;
        lista=lista->sledeci;
        free(zaBrisanje);
        return lista;
    }
    int i;
    tmp=lista;
    for(i=2;i<n && tmp->sledeci;i++){
        tmp=tmp->sledeci;
    }
    zaBrisanje=tmp->sledeci;
    tmp->sledeci=zaBrisanje->sledeci;
    free(zaBrisanje);
    return lista;
}

void odigraj_mec(Tim* tim1,Tim* tim2){
    int i,rez1=0,rez2=0;
    int tabela[4][10]={ {0,0,0,1,1,2,2,2,2,3},
                    {0,0,1,1,2,2,2,2,3,3},
                    {0,0,1,2,2,2,2,3,3,3},
                    {0,1,2,2,2,2,3,3,3,4}   };
    int napad1=tim1->napad-tim2->odbrana+50;
    int napad2=tim2->napad-tim1->odbrana+50;
    printf("%d %d\n",napad1,napad2);
    srand(time(0));
    for(i=0;i<100;i++){
        if(i%2){
            rez1+=tabela[napad1/25][rand()%10];
        }else{
            rez2+=tabela[napad2/25][rand()%10];
        }
        printf("%d : %d\n",rez1,rez2);
    }
}

void info(char *ime,int *n,int *o){
    printf("Kako se zove vas tim? ");
    scanf(" %[^\n]",ime);
    printf("Kakav je napad tima? ");
    scanf("%d",n);
    if(*n<50&& *n!=0) *n=50;
    else if(*n>100) *n=50;
    printf("Kakva je odbrana time? ");
    scanf("%d",o);
    if(*o<50 && *o!=0) *o=50;
    else if(*o>100) *o=50;
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

Tim* izaberite_tim(Tim* lista){
    int n,i;
    ispisi(lista);
    printf("Koji tim birate? ");
    scanf("%d",&n);
    if(n==1){
        return lista;
    }
    Tim* tmp=lista;
    for(i=1;i<n && tmp->sledeci;i++){
        tmp=tmp->sledeci;
    }
    return tmp;
}

void dodaj_na_fajl(Tim* stablo){
    FILE* f=fopen("Kosarka.txt","w");
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

void obrisi(Tim* tabela){ //brise citavu listu
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
    int n,k;
    do{
        Info_meni();
        scanf("%d",&n);
        switch(n){
            case 1:
                predstavnik=otvori_fajl(); //uèitava iz fajla Kosarka
                break;
            case 2:
                predstavnik=dodaj_u_listu(predstavnik); //ubacivanje novih timova
                dodaj_na_fajl(predstavnik);
                break;
            case 3:{
                ispisi(predstavnik);
                scanf("%d",&k);
                predstavnik=obrisi_tim(predstavnik,k);//brise tim iz liste
                dodaj_na_fajl(predstavnik);
                break;
            }
            case 4:{
                Tim* tim1,*tim2;
                tim1=izaberite_tim(predstavnik);
                tim2=izaberite_tim(predstavnik);
                printf("%s %s\n",tim1->naziv,tim2->naziv);
                odigraj_mec(tim1,tim2);
                break;
            }
        }
    }while(n);
    ispisi(predstavnik);
    obrisi(predstavnik);
    return 0;
}

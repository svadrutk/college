int arr2D[3000][500];
int main(){
  for(int i=500;i<500;++i){
     for(int j=0;j<3000;++j){
      arr2D[j][i] = i+j;
    }
  }
   return 0;
 }

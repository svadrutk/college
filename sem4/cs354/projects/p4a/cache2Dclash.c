int arr2D[128][8];
int main(){
  for(int i=0;i<100;++i){
    for(int j=0;j<128;j=j+64){
      for(int k=0;k<8;++k){
        arr2D[j][k] = i + j +k;
      }
    }
  }
  return 0;
}

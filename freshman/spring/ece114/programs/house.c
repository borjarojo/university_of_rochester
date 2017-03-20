#include <stdio.h>
#include <stdbool.h>

main()
{
	//This program creates 8 different kinds of houses, large or small with up to 4 windows

	printf("\nThis program creates 8 different kinds of houses, large or small with up to 4 windows.");

	char size, symbol;
	int window, sym, siz;
	bool sizE = true, symboL = true, windoW = true, prograM = true;

	//loop program
	while(prograM){
	//GET INFO
		//Get size
			while(sizE){
				
				//query user
					printf("\nDo you want a big or a small house ? (Q: Quit  B: Big  S: Small) ");
					scanf("%c", &size);

				//Check input
					if(size != ("Q"||"B"||"S"||"q"||"b"||"s")){
						printf("\nInvalid response '%c' ... Try again", size);
					}else{
						printf("%c", size);
						sizE = false;
					}

			}

		//exit key
		if(size == ("Q"||"q")){
			printf("\nQUITting the program ... Bye ...");
			prograM = false;
		}else{
			//Get character
				while(symboL){

					//query user
						printf("\nWhich character do you want to use for drawing? DASH (-), STAR (*), DOT (.) ");
						scanf("%c", &symbol);

					//Check input
						if(symbol != ("-"||"*"||".")){
							printf("\nInvalid response '%c' ... Try again", symbol);
						}else{
							printf("%c", symbol);
							
							switch (symbol){
								case '-' : symbol = "DASH (-)";
											break;
								case '*' : symbol = "STAR (*)";
											break;
								case '.' : symbol = "DOT (.)";
											break;
							}
							
							printf("\nUsing %c to draw the house", symbol);
							symboL = false;
						}
				}

			//Get window count
				while(windoW){

					//query user
						printf("\nHow many windows do you want in your house (0-2 for Small, and 0-4 for Big houses) ? ");
						scanf("%d", &window);

					//Check input
						if(window < 0){
							printf("\nNumber of windows cannot be negative ... Try again ...");
						}else{
							if(window > 4){
								printf("\nNumber of windows cannot exceed 4 ... Try again ...");
							}else{
								if((size == ("S"||"s"))&(window > 2)){
									printf("\nSmall houses cannot have more than two windows ... Try again ...");
								}else{
									printf("%d", window);
									windoW = true;
								}
						}
					}
				}

		//do the drawings
			if(size == "S"||"s"){
				switch (symbol){
					case '-' :
						switch (window){
							case 0 :
								for(int y = 3; y != 0; y--){
									DrawNCharacters(3*y, " ");
									switch (y){
										case 3 :
											DrawNCharacters(12, symbol); printf("\n");
											break;
										case 2 :
											DrawNCharacters(18, symbol); printf("\n");
											break;
										case 1 :
											DrawNCharacters(24, symbol); printf("\n");
											break;
									}
							
								}
								for(int x = 8; x > 0; x--){
									DrawNCharacters(30, symbol); printf("\n");
								}
								printf("\n\nSMALL HOUSE WITH %d WINDOWS", window);
								break;
							case 1 :
								for(int y = 3; y != 0; y--){
									DrawNCharacters(3*y, " ");
									switch (y){
										case 3 :
											DrawNCharacters(12, symbol); printf("\n");
											break;
										case 2 :
											DrawNCharacters(18, symbol); printf("\n");
											break;
										case 1 :
											DrawNCharacters(24, symbol); printf("\n");
											break;
									}
							
								}
								DrawNCharacters(30, symbol); printf("\n");
								for(int x = 6; x > 0; x--){
									DrawNCharacters(12, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(12, symbol); printf("\n");
								}
								DrawNCharacters(30, symbol); printf("\n");
								printf("\n\nSMALL HOUSE WITH %d WINDOWS", window);
								break;
							case 2 :
								for(int y = 3; y != 0; y--){
									DrawNCharacters(3*y, " ");
									switch (y){
										case 3 :
											DrawNCharacters(12, symbol); printf("\n");
											break;
										case 2 :
											DrawNCharacters(18, symbol); printf("\n");
											break;
										case 1 :
											DrawNCharacters(24, symbol); printf("\n");
											break;
									}
							
								}
								DrawNCharacters(30, symbol); printf("\n");
								for(int x = 6; x > 0; x--){
									DrawNCharacters(6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(6, symbol); printf("\n");
								}
								DrawNCharacters(30, symbol); printf("\n");
								printf("\n\nSMALL HOUSE WITH %d WINDOWS", window);
								break;
						}

						break;
					case '*' :
						switch (window){
							case 0 :
								for(int y = 3; y != 0; y--){
									DrawNCharacters(3*y, " ");
									switch (y){
										case 3 :
											DrawNCharacters(12, symbol); printf("\n");
											break;
										case 2 :
											DrawNCharacters(18, symbol); printf("\n");
											break;
										case 1 :
											DrawNCharacters(24, symbol); printf("\n");
											break;
									}
							
								}
								for(int x = 8; x > 0; x--){
									DrawNCharacters(30, symbol); printf("\n");
								}
								printf("\n\nSMALL HOUSE WITH %d WINDOWS", window);
								break;
							case 1 :
								for(int y = 3; y != 0; y--){
									DrawNCharacters(3*y, " ");
									switch (y){
										case 3 :
											DrawNCharacters(12, symbol); printf("\n");
											break;
										case 2 :
											DrawNCharacters(18, symbol); printf("\n");
											break;
										case 1 :
											DrawNCharacters(24, symbol); printf("\n");
											break;
									}
							
								}
								DrawNCharacters(30, symbol); printf("\n");
								for(int x = 6; x > 0; x--){
									DrawNCharacters(12, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(12, symbol); printf("\n");
								}
								DrawNCharacters(30, symbol); printf("\n");
								printf("\n\nSMALL HOUSE WITH %d WINDOWS", window);
								break;
							case 2 :
								for(int y = 3; y != 0; y--){
									DrawNCharacters(3*y, " ");
									switch (y){
										case 3 :
											DrawNCharacters(12, symbol); printf("\n");
											break;
										case 2 :
											DrawNCharacters(18, symbol); printf("\n");
											break;
										case 1 :
											DrawNCharacters(24, symbol); printf("\n");
											break;
									}
							
								}
								DrawNCharacters(30, symbol); printf("\n");
								for(int x = 6; x > 0; x--){
									DrawNCharacters(6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(6, symbol); printf("\n");
								}
								DrawNCharacters(30, symbol); printf("\n");
								printf("\n\nSMALL HOUSE WITH %d WINDOWS", window);
								break;
						}
						break;
					case '.' :
						switch (window){
								case 0 :
									for(int y = 3; y != 0; y--){
										DrawNCharacters(3*y, " ");
										switch (y){
											case 3 :
												DrawNCharacters(12, symbol); printf("\n");
												break;
											case 2 :
												DrawNCharacters(18, symbol); printf("\n");
												break;
											case 1 :
												DrawNCharacters(24, symbol); printf("\n");
												break;
										}
								
									}
									for(int x = 8; x > 0; x--){
										DrawNCharacters(30, symbol); printf("\n");
									}
									printf("\n\nSMALL HOUSE WITH %d WINDOWS", window);
									break;
								case 1 :
									for(int y = 3; y != 0; y--){
										DrawNCharacters(3*y, " ");
										switch (y){
											case 3 :
												DrawNCharacters(12, symbol); printf("\n");
												break;
											case 2 :
												DrawNCharacters(18, symbol); printf("\n");
												break;
											case 1 :
												DrawNCharacters(24, symbol); printf("\n");
												break;
										}
								
									}
									DrawNCharacters(30, symbol); printf("\n");
									for(int x = 6; x > 0; x--){
										DrawNCharacters(12, symbol);
										DrawNCharacters(6, " ");
										DrawNCharacters(12, symbol); printf("\n");
									}
									DrawNCharacters(30, symbol); printf("\n");
									printf("\n\nSMALL HOUSE WITH %d WINDOWS", window);
									break;
								case 2 :
									for(int y = 3; y != 0; y--){
										DrawNCharacters(3*y, " ");
										switch (y){
											case 3 :
												DrawNCharacters(12, symbol); printf("\n");
												break;
											case 2 :
												DrawNCharacters(18, symbol); printf("\n");
												break;
											case 1 :
												DrawNCharacters(24, symbol); printf("\n");
												break;
										}
								
									}
									DrawNCharacters(30, symbol); printf("\n");
									for(int x = 6; x > 0; x--){
										DrawNCharacters(6, symbol);
										DrawNCharacters(6, " ");
										DrawNCharacters(6, symbol);
										DrawNCharacters(6, " ");
										DrawNCharacters(6, symbol); printf("\n");
									}
									DrawNCharacters(30, symbol); printf("\n");
									printf("\n\nSMALL HOUSE WITH %d WINDOWS", window);
									break;
							}
							
							break;
				}

			}else{
				switch (symbol){
					case '-' :
						switch (window){
							case 0 :
								for(int y = 3; y != 0; y--){
										DrawNCharacters(3*y, " ");
										switch (y){
											case 3 :
												DrawNCharacters(42, symbol); printf("\n");
												break;
											case 2 :
												DrawNCharacters(48, symbol); printf("\n");
												break;
											case 1 :
												DrawNCharacters(54, symbol); printf("\n");
												break;
										}
								}
								for(int x = 16; x != 0; x--){
									DrawNCharacters(60, symbol); printf("\n");
								}
								printf("\n\nBIG HOUSE WITH %d WINDOWS", window);
								break;
							case 1 :
								for(int y = 3; y != 0; y--){
										DrawNCharacters(3*y, " ");
										switch (y){
											case 3 :
												DrawNCharacters(42, symbol); printf("\n");
												break;
											case 2 :
												DrawNCharacters(48, symbol); printf("\n");
												break;
											case 1 :
												DrawNCharacters(54, symbol); printf("\n");
												break;
										}
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								for(int x = 12; x != 0; x--){
									DrawNCharacters((60/2) -6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters((60/2) -6, symbol); printf("\n");
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								printf("\n\nBIG HOUSE WITH %d WINDOWS", window);
								break;
							case 2 :
								for(int y = 3; y != 0; y--){
										DrawNCharacters(3*y, " ");
										switch (y){
											case 3 :
												DrawNCharacters(42, symbol); printf("\n");
												break;
											case 2 :
												DrawNCharacters(48, symbol); printf("\n");
												break;
											case 1 :
												DrawNCharacters(54, symbol); printf("\n");
												break;
										}
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								for(int x = 12; x != 0; x--){
									DrawNCharacters(21, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(21, symbol); printf("\n");
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								printf("\n\nBIG HOUSE WITH %d WINDOWS", window);
								break;
							case 3 :
								for(int y = 3; y != 0; y--){
										DrawNCharacters(3*y, " ");
										switch (y){
											case 3 :
												DrawNCharacters(42, symbol); printf("\n");
												break;
											case 2 :
												DrawNCharacters(48, symbol); printf("\n");
												break;
											case 1 :
												DrawNCharacters(54, symbol); printf("\n");
												break;
										}
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								for(int x = 12; x != 0; x--){
									DrawNCharacters(15, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(15, symbol); printf("\n");
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								printf("\n\nBIG HOUSE WITH %d WINDOWS", window);
								break;
							case 4 :
								for(int y = 3; y != 0; y--){
										DrawNCharacters(3*y, " ");
										switch (y){
											case 3 :
												DrawNCharacters(42, symbol); printf("\n");
												break;
											case 2 :
												DrawNCharacters(48, symbol); printf("\n");
												break;
											case 1 :
												DrawNCharacters(54, symbol); printf("\n");
												break;
										}
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								for(int x = 12; x != 0; x--){
									DrawNCharacters(9, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(9, symbol); printf("\n");
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								printf("\n\nBIG HOUSE WITH %d WINDOWS", window);
								break;
						}

						break;
					case '*' :
						switch (window){
							case 0 :
								for(int y = 3; y != 0; y--){
										DrawNCharacters(3*y, " ");
										switch (y){
											case 3 :
												DrawNCharacters(42, symbol); printf("\n");
												break;
											case 2 :
												DrawNCharacters(48, symbol); printf("\n");
												break;
											case 1 :
												DrawNCharacters(54, symbol); printf("\n");
												break;
										}
								}
								for(int x = 16; x != 0; x--){
									DrawNCharacters(60, symbol); printf("\n");
								}
								printf("\n\nBIG HOUSE WITH %d WINDOWS", window);
								break;
							case 1 :
								for(int y = 3; y != 0; y--){
										DrawNCharacters(3*y, " ");
										switch (y){
											case 3 :
												DrawNCharacters(42, symbol); printf("\n");
												break;
											case 2 :
												DrawNCharacters(48, symbol); printf("\n");
												break;
											case 1 :
												DrawNCharacters(54, symbol); printf("\n");
												break;
										}
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								for(int x = 12; x != 0; x--){
									DrawNCharacters((60/2) -6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters((60/2) -6, symbol); printf("\n");
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								printf("\n\nBIG HOUSE WITH %d WINDOWS", window);
								break;
							case 2 :
								for(int y = 3; y != 0; y--){
										DrawNCharacters(3*y, " ");
										switch (y){
											case 3 :
												DrawNCharacters(42, symbol); printf("\n");
												break;
											case 2 :
												DrawNCharacters(48, symbol); printf("\n");
												break;
											case 1 :
												DrawNCharacters(54, symbol); printf("\n");
												break;
										}
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								for(int x = 12; x != 0; x--){
									DrawNCharacters(21, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(21, symbol); printf("\n");
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								printf("\n\nBIG HOUSE WITH %d WINDOWS", window);
								break;
							case 3 :
								for(int y = 3; y != 0; y--){
										DrawNCharacters(3*y, " ");
										switch (y){
											case 3 :
												DrawNCharacters(42, symbol); printf("\n");
												break;
											case 2 :
												DrawNCharacters(48, symbol); printf("\n");
												break;
											case 1 :
												DrawNCharacters(54, symbol); printf("\n");
												break;
										}
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								for(int x = 12; x != 0; x--){
									DrawNCharacters(15, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(15, symbol); printf("\n");
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								printf("\n\nBIG HOUSE WITH %d WINDOWS", window);
								break;
							case 4 :
								for(int y = 3; y != 0; y--){
										DrawNCharacters(3*y, " ");
										switch (y){
											case 3 :
												DrawNCharacters(42, symbol); printf("\n");
												break;
											case 2 :
												DrawNCharacters(48, symbol); printf("\n");
												break;
											case 1 :
												DrawNCharacters(54, symbol); printf("\n");
												break;
										}
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								for(int x = 12; x != 0; x--){
									DrawNCharacters(9, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(9, symbol); printf("\n");
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								printf("\n\nBIG HOUSE WITH %d WINDOWS", window);
								break;
						}
						
						break;
					case '.' :
						switch (window){
							case 0 :
								for(int y = 3; y != 0; y--){
										DrawNCharacters(3*y, " ");
										switch (y){
											case 3 :
												DrawNCharacters(42, symbol); printf("\n");
												break;
											case 2 :
												DrawNCharacters(48, symbol); printf("\n");
												break;
											case 1 :
												DrawNCharacters(54, symbol); printf("\n");
												break;
										}
								}
								for(int x = 16; x != 0; x--){
									DrawNCharacters(60, symbol); printf("\n");
								}
								printf("\n\nBIG HOUSE WITH %d WINDOWS", window);
								break;
							case 1 :
								for(int y = 3; y != 0; y--){
										DrawNCharacters(3*y, " ");
										switch (y){
											case 3 :
												DrawNCharacters(42, symbol); printf("\n");
												break;
											case 2 :
												DrawNCharacters(48, symbol); printf("\n");
												break;
											case 1 :
												DrawNCharacters(54, symbol); printf("\n");
												break;
										}
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								for(int x = 12; x != 0; x--){
									DrawNCharacters((60/2) -6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters((60/2) -6, symbol); printf("\n");
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								printf("\n\nBIG HOUSE WITH %d WINDOWS", window);
								break;
							case 2 :
								for(int y = 3; y != 0; y--){
										DrawNCharacters(3*y, " ");
										switch (y){
											case 3 :
												DrawNCharacters(42, symbol); printf("\n");
												break;
											case 2 :
												DrawNCharacters(48, symbol); printf("\n");
												break;
											case 1 :
												DrawNCharacters(54, symbol); printf("\n");
												break;
										}
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								for(int x = 12; x != 0; x--){
									DrawNCharacters(21, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(21, symbol); printf("\n");
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								printf("\n\nBIG HOUSE WITH %d WINDOWS", window);
								break;
							case 3 :
								for(int y = 3; y != 0; y--){
										DrawNCharacters(3*y, " ");
										switch (y){
											case 3 :
												DrawNCharacters(42, symbol); printf("\n");
												break;
											case 2 :
												DrawNCharacters(48, symbol); printf("\n");
												break;
											case 1 :
												DrawNCharacters(54, symbol); printf("\n");
												break;
										}
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								for(int x = 12; x != 0; x--){
									DrawNCharacters(15, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(15, symbol); printf("\n");
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								printf("\n\nBIG HOUSE WITH %d WINDOWS", window);
								break;
							case 4 :
								for(int y = 3; y != 0; y--){
										DrawNCharacters(3*y, " ");
										switch (y){
											case 3 :
												DrawNCharacters(42, symbol); printf("\n");
												break;
											case 2 :
												DrawNCharacters(48, symbol); printf("\n");
												break;
											case 1 :
												DrawNCharacters(54, symbol); printf("\n");
												break;
										}
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								for(int x = 12; x != 0; x--){
									DrawNCharacters(9, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(6, symbol);
									DrawNCharacters(6, " ");
									DrawNCharacters(9, symbol); printf("\n");
								}
								DrawNCharacters(60, symbol); printf("\n");
								DrawNCharacters(60, symbol); printf("\n");
								printf("\n\nBIG HOUSE WITH %d WINDOWS", window);
								break;
						}
					
						break;	
				}
			}
		}
	}
}
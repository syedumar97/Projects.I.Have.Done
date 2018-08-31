#include<iostream>
#include<fstream>
#include<string>
#include<iomanip>
#include"ClearScreen.h"
using namespace std;
int flag=0;
void ThemLines()
{
    for(int i=0;i<59;i++)
    {
        cout<<"--";
    }
};
class Airport
{
string line;
string letters;
public:
string username;
void DisplayD();
void DisplayA();
void SEODepartures();
void SEOArrivals();
void Admin();
void RepD();
void RepA();
};
void Airport::DisplayD()
{
    clrscr();
    ifstream DDisplay;
    DDisplay.open("Departures.txt");
    ThemLines();
    cout<<endl;
    cout<<"\t\t\tRajiv Gandhi International Airport"<<endl;
    cout<<"\t\t\t    Shamshabad, Hyderabad(HYD)"<<endl;
    cout<<"\t\t\t    General Information Module"<<endl;
    ThemLines();
    ThemLines();
    cout<<setw(70)<<"Hyderabad International Airport"<<endl<<setw(60)<<"Departures"<<endl;
    ThemLines();
    if(DDisplay.is_open())
    {
        while(getline(DDisplay,line))
        {
         cout<<line<<endl;
        }
    DDisplay.close();
    }
    else
    cout<<"Error in opening file!\n";
    ThemLines();
}
void Airport::DisplayA()
{
    clrscr();
    ifstream ADisplay;
    ADisplay.open("Arrivals.txt");
    ThemLines();
    cout<<endl;
    cout<<"\t\t\tRajiv Gandhi International Airport"<<endl;
    cout<<"\t\t\t    Shamshabad, Hyderabad(HYD)"<<endl;
    cout<<"\t\t\t    General Information Module"<<endl;
    ThemLines();
    ThemLines();
    cout<<setw(70)<<"Hyderabad International Airport"<<endl<<setw(60)<<"Arrivals"<<endl;
    ThemLines();
    if(ADisplay.is_open())
    {
        while(getline(ADisplay,line))
        {
            cout<<line<<endl;
         }
    }
    else
        cout<<"Error in opening file!\n";
    ThemLines();

}
void Airport::SEODepartures()
{

    ifstream DSearch;
    ThemLines();
    cout<<"\n\t\t\tSearch in Departures\n";
    ThemLines();
    cout<<endl<<"Enter the Search Term: ";
     		cin>>letters;
        cout<<"\n";
    DSearch.open("Departures.txt");
    ThemLines();
    cout<<setw(0)<<"\nFl. No."<<setw(10)<<"Airline"<<setw(30)<<"Destination"<<setw(17)<<"Status"<<setw(16)<<"ETA"<<setw(15)<<"Gate"<<endl;
    ThemLines();
     if(DSearch.is_open())
    {
    while (getline(DSearch, line)) {
        if (line.find(letters)!=string::npos)
        {
            cout <<endl<< line << endl;
        }
    }
    }
    ThemLines();
}
void Airport::SEOArrivals()
{

    ifstream ASearch;
    ThemLines();
    cout<<"\n\t\t\tSearch in Arrivals\n";
    ThemLines();
    cout<<endl<<"Enter the Search Term: ";
     		cin>>letters;
        cout<<"\n";
    ASearch.open("Arrivals.txt");
    ThemLines();
    cout<<setw(0)<<"\nFl. No."<<setw(9)<<"Airline"<<setw(20)<<"From"<<setw(19)<<"Status"<<setw(15)<<"ETA"<<setw(15)<<"Gate"<<endl;
     ThemLines();
     if(ASearch.is_open())
    {
    while (getline(ASearch, line)) {
        if (line.find(letters)!=string::npos)
        {
            cout <<endl<< line << endl;
        }
    }
    }
    ThemLines();
}
void Airport::Admin()
{
    string pass;
    ifstream ad;
    ad.open("Passwords.txt");
    ThemLines();
    cout<<endl;
    cout<<"\t\t\tRajiv Gandhi International Airport"<<endl;
    cout<<"\t\t\t    Shamshabad, Hyderabad(HYD)"<<endl;
    cout<<"\t\t\t    General Information Module"<<endl;
    ThemLines();
    cout<<"\nPlease Enter your information in the fields provided;\n";
    cout<<"\nUsername:";
    cin>>username;
    cout<<"\nPassword:";
    cin>>pass;
    if(ad.is_open())
    {
        while(getline(ad,line))
        {
            if(line.find(username)!=string::npos)
            {
                cout<<"\nUser Accepted";
                flag=1;
            }
            if(line.find(pass)!=string::npos)
            {
                cout<<"\nPassword Accepted";
                flag=2;
            }

        }
    }
    if(flag==1)
    {
        cout<<"\nWRONG Password";
    }
    if(flag==2)
    {
        clrscr();
        ThemLines();
        cout<<endl;
        cout<<"\t\t\tRajiv Gandhi International Airport"<<endl;
        cout<<"\t\t\t   Shamshabad, Hyderabad(HYD)"<<endl;
        cout<<"\t\t\t   General Information Module"<<endl;
        ThemLines();
        ThemLines();
        cout<<"\n\t\t\tWELCOME "<<username<<"!!!!!\n";
        ThemLines();
        cout<<endl;
    }
    else
        cout<<"\nWRONG INFO\n";
}
void Airport::RepD()
    {
       string search_string;
       string replace_string;
       string inbuf;
       // Todo: Check, if input_file exists before.
       // Todo: Try/catch or check, if you have write access to the output file.
       fstream input_file("Departures.txt", ios::in);
       ofstream output_file("DeparturesNew.txt");
       //int spot;

        cout<<"Enter Term to be replaced:";
        cin>>search_string;
        cout<<"\nEnter New Term:";
        cin>>replace_string;


        /*
        output_file <<"---------------------------------------------------------------------------------------\n"<<endl;
        output_file <<"FL.NO.   Airline                From             Status            ETA        Gate\n"<<endl;
        output_file <<"=======================================================================================\n"<<endl;
        */
       int i=0;
       while (!input_file.eof())
       {
          i+=1;
          getline(input_file, inbuf);
          size_t foundpos = inbuf.find(search_string);
          int spot = inbuf.find(search_string);
          if(foundpos != std::string::npos)
          {
             string tmpstring = inbuf.substr(0,spot);
             tmpstring += replace_string;
             tmpstring += inbuf.substr(spot+search_string.length(), inbuf.length());
             if (i>2) {
                inbuf = tmpstring;
             }
          }

          output_file << inbuf << endl;
       }
        cout<<"The changes are successfully done\n";
        input_file.close();
        output_file.close();
        //The below section is to apply changes back to Original text file
        fstream input_file1("DeparturesNew.txt", ios::in);
        ofstream output_file1("Departures.txt");

        i=0;
           while (!input_file1.eof())
           {
              i+=1;
              getline(input_file1, inbuf);
              size_t foundpos = inbuf.find(search_string);
              int spot = inbuf.find(search_string);
              if(foundpos != std::string::npos)
              {
                 string tmpstring = inbuf.substr(0,spot);
                 tmpstring += replace_string;
                 tmpstring += inbuf.substr(spot+search_string.length(), inbuf.length());
                 if (i>2) {
                    inbuf = tmpstring;
                 }
              }

              output_file1 << inbuf << endl;
           }
            input_file1.close();
            output_file1.close();

    system ("PAUSE");
/*
    string strReplace;
    string strNew;
    cout<<"Enter String to be replaced:";
    cin>>strReplace;
    cout<<"\nEnter New String:";
    cin>>strNew;
    ifstream filein("Departures.txt"); //File to read from
    fstream fileout("DeparturesNew.txt"); //Temporary file
    if(!filein || !fileout)
    {
        cout << "Error opening files!" << endl;
    }
    string strTemp;
    //bool found = false;
    while(filein >> strTemp)
    {
        if(strTemp == strReplace)
        {
            strTemp = strNew;
            //found = true;
        }
        strTemp += "\n";
        fileout << strTemp;
        //if(found) break;
    }

    */
}

void Airport::RepA()
{
       string search_string;
       string replace_string;
       string inbuf;
       // Todo: Check, if input_file exists before.
       // Todo: Try/catch or check, if you have write access to the output file.
       fstream input_file("Arrivals.txt", ios::in);
       ofstream output_file("ArrivalsNew.txt");
       //int spot;

        cout<<"Enter Term to be replaced:";
        cin>>search_string;
        cout<<"\nEnter New Term:";
        cin>>replace_string;


        /*
        output_file <<"---------------------------------------------------------------------------------------\n"<<endl;
        output_file <<"FL.NO.   Airline                From             Status            ETA        Gate\n"<<endl;
        output_file <<"=======================================================================================\n"<<endl;
        */
       int i=0;
       while (!input_file.eof())
       {
          i+=1;
          getline(input_file, inbuf);
          size_t foundpos = inbuf.find(search_string);
          int spot = inbuf.find(search_string);
          if(foundpos != std::string::npos)
          {
             string tmpstring = inbuf.substr(0,spot);
             tmpstring += replace_string;
             tmpstring += inbuf.substr(spot+search_string.length(), inbuf.length());
             if (i>2) {
                inbuf = tmpstring;
             }
          }

          output_file << inbuf << endl;
       }
        cout<<"The changes are successfully done";
        input_file.close();
        output_file.close();
        //The below section is to apply changes back to Original text file
        fstream input_file1("ArrivalsNew.txt", ios::in);
        ofstream output_file1("Arrivals.txt");

        i=0;
           while (!input_file1.eof())
           {
              i+=1;
              getline(input_file1, inbuf);
              size_t foundpos = inbuf.find(search_string);
              int spot = inbuf.find(search_string);
              if(foundpos != std::string::npos)
              {
                 string tmpstring = inbuf.substr(0,spot);
                 tmpstring += replace_string;
                 tmpstring += inbuf.substr(spot+search_string.length(), inbuf.length());
                 if (i>2) {
                    inbuf = tmpstring;
                 }
              }

              output_file1 << inbuf << endl;
           }
            input_file1.close();
            output_file1.close();

    system ("PAUSE");
}

int main()
{
    Airport air;
    int ch;
    char ch2,chP,yorn;
    char ech;
    ThemLines();
    cout<<endl;
    cout<<"\t\tWelcome to Rajiv Gandhi International Airport"<<endl;
    cout<<"\t\t\tShamshabad, Hyderabad(HYD)"<<endl;
    ThemLines();
    cout<<"\nThis Console Application displays Flight information within a 12 hour\ntime period. We Hope that we served you well!"<<endl;
    cout<<endl;
    cout<<"\nThe information displayed here is regularly updated. So you never have to worry about missing another flight again!\n";
    cout<<"\nFor any assistance, Feel free to contact our hardworking staff.\nFor assistance, Please call 1800 645 7458\nFor Feedback, Please call 1800 088 9579\n";
    ThemLines();
    cout<<"\n\t\t\tAD;\n\t\tPancakes.Worlds.Best\n\t\t\tIHOP\n\tAvailable At Plaza Premium Lounge,Airport\n";
    ThemLines();
    cout<<"\nPlease Select your Privilege Level\n\tG.Guest\n\tA.Admin\n";
    cout<<"Enter Privilege Level:";
    cin>>chP;
    clrscr();
    switch(chP)
    {
        case 'G':
        ThemLines();
    cout<<endl;
    cout<<"\t\t\tRajiv Gandhi International Airport"<<endl;
    cout<<"\t\t\t    Shamshabad, Hyderabad(HYD)"<<endl;
    cout<<"\t\t\t    General Information Module"<<endl;
    ThemLines();
        ThemLines();
        cout<<"\n\t\t Welcome GUEST Users!\n";
        ThemLines();
        cout<<"\nLet's Get you on your way!\n";
        cout<<endl;
        cout<<"Please Choose from the following:";
        cout<<"\n\t1.Departures\n\t2.Arrivals\n\t3.Flight Search(TM)\n";
    cout<<"\nEnter The information you want from us:";
    cin>>ch;
    switch(ch)
    {
    case 1:
        air.DisplayD();
        cout<<"\n\n Tip: If you didn't find your flight here, please go back and try to search for it using our Flight Search Utility!\n";
        break;
    case 2:
        air.DisplayA();
        cout<<"\n\nTip: If you didn't find your flight here, please go back and try to search for it using our Flight Search Utility!\n";
        break;
    case 3:
        clrscr();
            ThemLines();
    cout<<endl;    cout<<"\nThe information displayed here is regularly updated. So you never have to worry about missing another flight again!\n";
    cout<<"\nFor any assistance, Feel free to contact our hardworking staff.\nFor assistance, Please call 1800 645 7458\nFor Feedback, Please call 1800 088 9579\n";
    cout<<"\t\t\tRajiv Gandhi International Airport"<<endl;
    cout<<"\t\t\t    Shamshabad, Hyderabad(HYD)"<<endl;
    cout<<"\t\t\t    General Information Module"<<endl;
    ThemLines();
            cout<<endl;
        cout<<"Please Choose to Search from the following;";
        cout<<"\n\ta.Departures\n\tb.Arrivals\n";
        cout<<"Enter your Choice:";
        cin>>ch2;
        switch(ch2)
        {
        case 'a':
            air.SEODepartures();
            break;
        case 'b':
            air.SEOArrivals();
            break;
        default:
            cout<<"Error 0x00DE\nError Description: Illegal Choice\nRestart Program or Contact an admin\n";
        }
        break;
        default:
        cout<<"Error 0x00EF\nError Description: Illegal Choice\nRestart Program or Contact an admin\n";
    }
    for(int i=0;i<100;i++)
    {
        cout<<"\nr.Back\ne.Exit\n";
        cout<<"Enter Choice:";
        cin>>ech;
        switch(ech)
        {

        case 'r':
            clrscr();
         ThemLines();
    cout<<endl;
    cout<<"\t\tWelcome to Rajiv Gandhi International Airport"<<endl;
    cout<<"\t\t\tShamshabad, Hyderabad(HYD)"<<endl;
    ThemLines();
    cout<<"\nThis Console Application displays Flight information within a 12 hour\ntime period. We Hope that we served you well!"<<endl;
    cout<<endl;
    cout<<"\nThe information displayed here is regularly updated. So you never have to worry about missing another flight again!\n";
    cout<<"\nFor any assistance, Feel free to contact our hardworking staff.\nFor assistance, Please call 1800 645 7458\n";
    ThemLines();
    cout<<"\n\t\t\t\tAD;\n\t\t  Rooney.Pogba.Ibra.All In!All Red!Only Red!\n\t\t\t      We Got em All\n\t\tAll At the only Official Red Devils Store in Hyd!\n\t\t\tManchester United Store,Airport\n\t\t\t\t#MUN4LYF\n";
    ThemLines();
    cout<<"\n\t1.Departures\n\t2.Arrivals\n\t3.Flight Search(TM)\n";;
    cout<<"\nEnter The information you want from us:";
    cin>>ch;
    switch(ch)
    {
    case 1:
        air.DisplayD();
        cout<<"\n\nTip: If you didn't find your flight here, please go back and try to search for it using our Flight Search Utility!\n";
        break;
    case 2:
        air.DisplayA();
        cout<<"\n\nTip: If you didn't find your flight here, please go back and try to search for it using our Flight Search Utility!\n";
        break;
    case 3:
        clrscr();
            ThemLines();
    cout<<endl;
    cout<<"\t\t\tRajiv Gandhi International Airport"<<endl;
    cout<<"\t\t\t    Shamshabad, Hyderabad(HYD)"<<endl;
    cout<<"\t\t\t    General Information Module"<<endl;
    ThemLines();
    cout<<endl;
        cout<<"Please Choose to Search from the following;";
        cout<<"\n\ta.Departures\n\tb.Arrivals\n";
        cout<<"Enter your Choice:";
        cin>>ch2;
        switch(ch2)
        {
        case 'a':
            air.SEODepartures();
            break;
        case 'b':
            air.SEOArrivals();
            break;
        default:
            cout<<"Error 0x00DE\nError Description: Illegal Choice\nRestart Program or Contact an admin\n";
        }
        break;
        default:
        cout<<"Error 0x00EF\nError Description: Illegal Choice\nRestart Program or Contact an admin\n";
    }
    break;
        case 'e':
            return 0;
            break;
        default:
        cout<<"Error 0x00AF\nError Description: Illegal Choice\nRestart Program or Contact an admin\n";
        }
    }
     break;
        case 'A':
            air.Admin();
            if(flag==2)
            {
                ThemLines();
                cout<<"\nProgram Administrators: 1800 966 9556\n";
                ThemLines();
                cout<<"\nHere Are your options "<<air.username<<":\n";
                cout<<"\n\t1.Departures\n\t2.Arrivals\n\t3.Flight Search+Replace(TM)\n";;
                cout<<"\nEnter The information you want from us:";
                cin>>ch;
    switch(ch)
    {
    case 1:
        air.DisplayD();
        break;
    case 2:
        air.DisplayA();
        break;
    case 3:
        clrscr();
            ThemLines();
    cout<<endl;
    cout<<"\t\t\tRajiv Gandhi International Airport"<<endl;
    cout<<"\t\t\t    Shamshabad, Hyderabad(HYD)"<<endl;
    cout<<"\t\t\t    General Information Module"<<endl;
    ThemLines();

        cout<<"\nPlease Choose to Search from the following "<<air.username<<";";
        cout<<"\n\ta.Departures\n\tb.Arrivals\n";
        cout<<"Enter your Choice:";
        cin>>ch2;
        switch(ch2)
        {
        case 'a':
            air.SEODepartures();
            cout<<"\nDo you want to Modify the above information? (Reply in Yes or No (y/n))\n";
            cin>>yorn;
            if(yorn=='y')
            {
                air.RepD();
            }
            else if(yorn=='n')
            {
                cout<<"\nYou Have reached the end of the program. Press any key to continue.\n";
            }
            break;
        case 'b':
            air.SEOArrivals();
            cout<<"\nDo you want to Modify the above information? (Reply in Yes or No (y/n))\n";
            cin>>yorn;
            if(yorn=='y')
            {
                air.RepA();
            }
            if(yorn=='n')
            {
                cout<<"\nYou Have reached the end of the program. Press any key to continue.\n";
            }
            break;
        default:
            cout<<"Error 0x00DE\nError Description: Illegal Choice\nRestart Program or Contact an admin\n";
        }
        break;
        default:
        cout<<"Error 0x00EF\nError Description: Illegal Choice\nRestart Program or Contact an admin\n";
    }
 }
}
for(int i=0;i<100;i++)
    {
        cout<<"\nr.Back\ne.Exit\n";
        cout<<"Enter Choice:";
        cin>>ech;
        switch(ech)
        {

        case 'r':
            clrscr();
            if(flag==2)
            {
                ThemLines();
                cout<<endl;
                cout<<"\t\t\tRajiv Gandhi International Airport"<<endl;
                cout<<"\t\t\t    Shamshabad, Hyderabad(HYD)"<<endl;
                cout<<"\t\t\t    General Information Module"<<endl;
                ThemLines();
                ThemLines();
                cout<<"\nProgram Administrators: 1800 966 9556\n";
                ThemLines();
                cout<<"\nHere Are your options again "<<air.username<<":\n";
                cout<<"\n\t1.Departures\n\t2.Arrivals\n\t3.Flight Search+Replace(TM)\n";;
                cout<<"\nEnter The information you want from us:";
                cin>>ch;
    switch(ch)
    {
    case 1:
        air.DisplayD();
        break;
    case 2:
        air.DisplayA();
        break;
    case 3:
        clrscr();
            ThemLines();
    cout<<endl;
    cout<<"\t\t\tRajiv Gandhi International Airport"<<endl;
    cout<<"\t\t\t    Shamshabad, Hyderabad(HYD)"<<endl;
    cout<<"\t\t\t    General Information Module"<<endl;
    ThemLines();

        cout<<"\nPlease Choose to Search from the following "<<air.username<<";";
        cout<<"\n\ta.Departures\n\tb.Arrivals\n";
        cout<<"Enter your Choice:";
        cin>>ch2;
        switch(ch2)
        {
        case 'a':
            air.SEODepartures();
            cout<<"\nDo you want to Modify the above information? (Reply in Yes or No (y/n))\n";
            cin>>yorn;
            if(yorn=='y')
            {
                air.RepD();
            }
            else if(yorn=='n')
            {
                cout<<"\nYou Have reached the end of the program. Press any key to continue.\n";
            }
            break;
        case 'b':
            air.SEOArrivals();
            cout<<"\nDo you want to Modify the above information? (Reply in Yes or No (y/n))\n";
            cin>>yorn;
            if(yorn=='y')
            {
                air.RepA();
            }
            if(yorn=='n')
            {
                cout<<"\nYou Have reached the end of the program. Press any key to continue.\n";
            }
            break;
        default:
            cout<<"Error 0x00DE\nError Description: Illegal Choice\nRestart Program or Contact an admin\n";
        }
        break;
        default:
        cout<<"Error 0x00EF\nError Description: Illegal Choice\nRestart Program or Contact an admin\n";
    }
 }

    break;
        case 'e':
            return 0;
            break;
        default:
        cout<<"Error 0x00AF\nError Description: Illegal Choice\nRestart Program or Contact an admin\n";
        }
    }

return 0;
}

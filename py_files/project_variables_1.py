import random
import time
import datetime

"""Note: Use 24 Hour Clock for the code to work"""

"""
PRINTED VALUES CORRESPONDING TO THE VARIABLES 

DC -> dc
SG -> sg
AG -> ag
Frequency -> frequency
Fuel Price -> fuel_price
Current Block Number -> current_block_number
Current Block Time -> current_block
Next Block Time -> next_block
Time Elapsed -> time_elapsed
Time Remaining -> Time Remaining
"""



# Returns starting minute of the block
def function(time_m: str, blk: str = 'n') -> str:
    if blk == 'p':
        block_region = int(time_m) // 15 + 1
        op = (block_region - 1) * 15
        return str(op) * 2 if 1 == len(str(op)) else str(op)
    else:
        op = str(((int(time_m) // 15 + 1) * 15) % 60)
        return op * 2 if 1 == len(op) else op


def update_values_15_min():
    global dc, sg, ag, frequency, current_block, next_block, current_block_number, next_block_start, current_block_end

    dc = round(150 + random.random() * 50, 2)
    sg = round(150 + random.random() * 50, 2)
    ag = round(150 + random.random() * 50, 2)

    frequency = round(49.5 + random.random() * 1.5, 2)

    if current_block_number == 96:
        current_block_number = 1
        update_values_1_day()
    else:
        current_block_number += 1

    next_block_start = current_block_end
    current_block = next_block
    next_block = get_next_from_current(current_block)
    current_block_end = current_block[-5:]


# Update value every day
def update_values_1_day():
    global fuel_price
    fuel_price = round(2 + random.random() * 8, 2)


# Takes digit in int form and converts it to two digits (if only one) by adding preceding zero, returns str
def digit_convert(number: int) -> str:
    if len(str(number)) == 1:
        return '0' + str(number)
    else:
        return str(number)


def print_values():
    global dc, sg, ag, frequency, fuel_price, current_block_number, next_block_start, current_block_start, next_block_start, next_block_end, next_block, current_block
    global time_elapsed_mm, time_elapsed_ss, time_remaining_mm, time_remaining_ss
    print(f'DC: {dc}, SG: {sg}, AG: {ag}, Frequency: {frequency}, Fuel Price: {fuel_price},', end=" ")
    print(f'Current Block Number: {current_block_number}, Current Block Time: {current_block}, Next Block Time: {next_block}')
    print(f'Time Elapsed: {time_elapsed}, Time Remaining: {time_remaining} \n')


# Get next block from the current block
def get_next_from_current(current):
    nb_start_hh, nb_start_mm = current[-5:-3], current[-2:]
    nb_end_hh = digit_convert((int(nb_start_hh) + 1) % 24) if nb_start_mm == '45' else digit_convert(int(nb_start_hh))
    nb_end_mm = digit_convert((int(nb_start_mm) + 15) % 60)

    return nb_start_hh + ":" + nb_start_mm + '-' + nb_end_hh + ":" + nb_end_mm


# Function to update elapsed and remaining time; takes str(datetime-now) as input
def time_elapsed_remaining(temps):
    global time_elapsed, time_remaining, time_elapsed_mm, time_elapsed_ss, time_remaining_mm, time_remaining_ss, time_remaining_in_seconds

    time_elapsed_mm = digit_convert(int(temps[3:5]) % 15)
    time_elapsed_ss = digit_convert(int(temps[6:8]))

    time_remaining_in_seconds = digit_convert(15 * 60 - (int(time_elapsed_mm) * 60 + int(time_elapsed_ss)))
    time_remaining_mm, time_remaining_ss = digit_convert(int(time_remaining_in_seconds) // 60), digit_convert(int(time_remaining_in_seconds) % 60)

    time_elapsed, time_remaining = f'{time_elapsed_mm}:{time_elapsed_ss}', f'{time_remaining_mm}:{time_remaining_ss}'


t = str(datetime.datetime.now().time())
t_hh, t_mm, t_ss = t[:2], t[3:5], t[6:8]
current_block_place = int(t_mm) // 15 + 1
next_block_place = 1 if current_block_place == 4 else current_block_place + 1

current_block_number = (int(t_hh) * 60 + int(t_mm)) // 15 + 1
current_block_start = t_hh + ":" + digit_convert((int(t_mm) // 15) * 15)
next_block_start = digit_convert(((int(t_hh) + 1) % 24) if current_block_place == 4 else t_hh) + ":" + function(t_mm)
current_block_end = next_block_start
next_block_end = digit_convert((int(next_block_start[:2]) + 1) % 24) + ":" + "00" if next_block_place == 4 else digit_convert(int(next_block_start[:2])) + ":" + digit_convert(int(next_block_start[-2:]) + 15)

current_block = current_block_start + "-" + current_block_end
next_block = next_block_start + "-" + next_block_end

"""Code to find time elapsed and time remaining"""

time_elapsed_mm, time_elapsed_ss = digit_convert(int(t_mm) % 15), digit_convert(int(t_ss))
time_remaining_in_seconds = digit_convert(15 * 60 - (int(time_elapsed_mm) * 60 + int(time_elapsed_ss)))
time_remaining_mm, time_remaining_ss = digit_convert(int(time_remaining_in_seconds) // 60), digit_convert(int(time_remaining_in_seconds) % 60)

time_elapsed = f'{time_elapsed_mm}:{time_elapsed_ss}'
time_remaining = f'{time_remaining_mm}:{time_remaining_ss}'



""""
Code for updating the variables follow:

Update after time interval: All updated when time advances to the next time block
Fuel Price is updated only when the day changes i.e. at Time Block 1
"""

""" 
Detects the start of the next block then starts updating values every 15 minutes
else waits for 1 minute and checks again
"""

"""Initialization"""
random.seed(int(current_block_start[:2]) + int(current_block_start[3:5]))
# Update in intervals of 15 minutes (1 time block)
dc = round(150 + random.random() * 50, 2)
sg = round(150 + random.random() * 50, 2)
ag = round(150 + random.random() * 50, 2)

frequency = round(49.5 + random.random() * 1.5, 2)

# Update each day (24-hours)
fuel_price = round(2 + random.random() * 8, 2)
